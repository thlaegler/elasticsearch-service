package com.laegler.microservice.elasticsearch.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.elasticsearch.action.admin.indices.get.GetIndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.AliasBuilder;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.laegler.microservice.elasticsearch.elasticsearch.ProductIndexProperties;
import com.laegler.microservice.elasticsearch.elasticsearch.ProductSearchRepository;
import com.laegler.microservice.elasticsearch.model.Product;

@Service
public class ProductIndexService extends AbstractEsService {

  private static final Logger LOG = LoggerFactory.getLogger(ProductIndexService.class);

  @Autowired
  private ProductIndexProperties props;

  @Autowired
  private Client esClient;

  @Autowired
  private ElasticsearchTemplate esTemplate;

  @Autowired
  private ProductSearchRepository esRepository;

  @Autowired
  private ProductService productService;

  public String doIndexing(boolean dropOldIndex) {
    LOG.info("Trying to index products. Will drop old index: {} ...", dropOldIndex);

    if (dropOldIndex == true && esTemplate.indexExists(props.getIndexName())) {
      esTemplate.deleteIndex(props.getIndexName());
    }

    // String randomId = UUID.randomUUID().toString();

    // String docId = template.index(new IndexQueryBuilder().withIndexName(props.getIndexName())
    // .withType(props.getIndexType()).withId(randomId).withObject(createSearchIndex()).build());

    // IndexResponse response = esClient.prepareIndex(props.getIndexName(), props.getIndexType(),
    // randomId)
    // .setSource(props.getIndexName(), createSearchIndex()).execute().actionGet();
    // String docId = response.getId();

    List<IndexQuery> indexQueries = new ArrayList<>();
    productService.getAllProducts().forEach((product -> {
      indexQueries.add(queryProductIndex(product));
    }));

    esTemplate.bulkIndex(indexQueries);

    refreshIndex();

    long itemsInIndex = esTemplate.count(queryProductsAll(), Product.class);
    LOG.info("Indexing done. Indexed {} products.", itemsInIndex);

    return "" + itemsInIndex;
  }

  public String reindex() {
    return doIndexing(true);
  }

  public void refreshIndex() {
    LOG.debug("Trying to refresh Product Search Index ...");

    esTemplate.refresh(Product.class);

    // RefreshResponse response =
    // esClient.admin().indices().prepareRefresh(props.getIndexName()).execute().actionGet();

    LOG.debug("Product Search Index Refreshing done");
  }


  public void addAlias(String alias) throws InterruptedException, ExecutionException {
    AliasBuilder aliasBuilder = new AliasBuilder();
    esTemplate.addAlias(
        aliasBuilder.withAliasName("the_alias").withIndexName(props.getIndexName()).build());

    // IndicesAliasesRequest request = new IndicesAliasesRequest();
    // request.addAliasAction(
    // new AliasAction(AliasAction.Type.ADD).alias("the_alias").index(props.getIndexName())
    // .searchRouting("the_search_routing").indexRouting("the_index_routing"));
    // IndicesAliasesResponse response = esClient.admin().indices().aliases(request).get();
  }


  public long countIndexedProducts() {
    return esRepository.count();
  }

  private void replaceIndex(String indexName) {
    if (esTemplate.indexExists(indexName)) {
      esTemplate.deleteIndex(indexName);
    }
  }

  private void replaceIndexByAlias(String alias, Product productSearch) {
    if (esTemplate.indexExists(alias)) {
      esTemplate.deleteIndex(alias);
    }
  }

  private SearchQuery queryProductsAll() {
    return new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchAllQuery()).build();
  }

  private SearchQuery queryProducts(String queryString) {
    return new NativeSearchQueryBuilder().withQuery(QueryBuilders.queryStringQuery(queryString))
        .build();
  }

  private IndexQuery queryProductIndex(Product product) {
    return new IndexQueryBuilder().withIndexName(props.getIndexName())
        .withType(props.getIndexType()).withObject(product).build();
  }

  private GetIndexResponse getIndexMetadata(String indexName) {
    LOG.info("Trying to load Metadata of Index {} ...", indexName);
    GetIndexResponse response = esClient.admin().indices().prepareGetIndex().execute().actionGet();
    LOG.info("Loading of Metadata  for Index {} done:", indexName);
    LOG.info("  Settings: " + response.getSettings().toString());
    LOG.info("  Mappings: " + response.getMappings().toString());
    LOG.info("  Aliases: " + response.getAliases().toString());
    LOG.info("  Headers: " + response.getHeaders().toString());
    return response;
  }

}
