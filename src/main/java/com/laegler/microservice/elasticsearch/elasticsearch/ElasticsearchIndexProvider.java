package com.laegler.microservice.elasticsearch.elasticsearch;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.elasticsearch.client.Client;
import org.elasticsearch.node.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Component;

import com.laegler.microservice.elasticsearch.model.Product;
import com.laegler.microservice.elasticsearch.service.ProductIndexService;

import lombok.Data;

@Data
@Component
public class ElasticsearchIndexProvider {

  private static final Logger LOG = LoggerFactory.getLogger(ElasticsearchIndexProvider.class);

  @Autowired
  private ElasticsearchProperties esProperties;

  @Autowired
  private ProductIndexProperties productProperties;

  @Autowired
  private Node esNode;

  @Autowired
  private Client esClient;

  @Autowired
  private ProductIndexService productIndexService;

  @Autowired
  private ElasticsearchTemplate esTemplate;

  @PostConstruct
  public void init() {
    LOG.debug("Trying to initialize and load Products into Product Search Index...");

    // Initialize Product Search Index
    esTemplate.deleteIndex(Product.class);
    esTemplate.createIndex(Product.class);
    esTemplate.putMapping(Product.class);
    // esTemplate.refresh(Product.class);
    String productIndexId = productIndexService.index(true);

    LOG.debug("Initialization of Product Search Index done. Index ID: {}.", productIndexId);

    // Probe Index (count products)
    LOG.debug("  Loaded {} Products into Product Search Index {}.",
        productIndexService.countIndexedProducts(), productIndexId);
    // new IndexQueryBuilder().withIndexName(productProperties.getIndexName())
    // .withObject(productSearch).build();
    // QueryBuilder queryBuilder = QueryBuilders.wildcardQuery("name", "a*");
    // SearchRequestBuilder searchRequestBuilder =
    // esClient.prepareSearch(productProperties.getIndexName());
    // searchRequestBuilder.setTypes(productProperties.getIndexType());
    // searchRequestBuilder.setSearchType(SearchType.DEFAULT);
    // searchRequestBuilder.setQuery(queryBuilder);
  }

  @PreDestroy
  public void destroy() {
    esClient.close();
    esNode.close();
  }
}
