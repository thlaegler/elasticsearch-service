package com.laegler.microservice.elasticsearch.service;

import java.lang.reflect.Field;
import java.util.List;

import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.InternalAggregations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.facet.FacetResult;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.laegler.microservice.elasticsearch.elasticsearch.ProductSearchRequest;
import com.laegler.microservice.elasticsearch.model.Product;

@Service
public class ProductSearchService extends AbstractEsService {

  private static final Logger LOG = LoggerFactory.getLogger(ProductSearchService.class);

  @Autowired
  private ElasticsearchTemplate template;

  public Page<Product> searchPaged(ProductSearchRequest searchRequest) {
    LOG.info("Trying to search for products with search request: {}", searchRequest.toString());
    Page<Product> it =
        template.queryForPage(createSearchQuery(searchRequest.getQueryString()), Product.class);

    LOG.info("Searched for products and found {} in index", it.getTotalElements());
    return fixEmptyPage(it);
  }

  public Page<Product> searchPaged(String queryString) {
    LOG.info("Trying to search for products with query string: {}", queryString);
    Page<Product> it = template.queryForPage(createSearchQuery(queryString), Product.class);

    LOG.info("Searched for products and found {} in index", it.getTotalElements());
    return fixEmptyPage(it);
  }

  public List<Product> search(ProductSearchRequest searchRequest) {
    LOG.info("Trying to search for products with search request: {}", searchRequest.toString());
    List<Product> it =
        template.queryForList(createSearchQuery(searchRequest.getQueryString()), Product.class);

    LOG.info("Searched for products and found {} in index", it.size());
    return it;
  }

  public List<Product> search(String queryString) {
    LOG.info("Trying to search for products with query string: {}", queryString);

    List<Product> it = template.queryForList(createSearchQuery(queryString), Product.class);

    LOG.info("Searched for products and found {} in index", it.size());
    return it;
  }

  /**
   * Workaround for Spring-data bug, see https://jira.spring.io/browse/DATAES-274 and
   * https://github.com/spring-projects/spring-data-elasticsearch/pull/175
   * 
   * @param page
   * @return
   */
  @SuppressWarnings("deprecation")
  private <T> Page<T> fixEmptyPage(Iterable<T> page) {
    AggregatedPageImpl<T> pageResult = (AggregatedPageImpl<T>) page;
    Aggregations aggregations = pageResult.getAggregations();
    if (aggregations == null) {
      Field field = ReflectionUtils.findField(AggregatedPageImpl.class, "aggregations");
      ReflectionUtils.makeAccessible(field);
      ReflectionUtils.setField(field, pageResult, InternalAggregations.EMPTY);
    }
    List<FacetResult> facets = pageResult.getFacets();
    if (facets == null) {
      Field field = ReflectionUtils.findField(AggregatedPageImpl.class, "facets");
      ReflectionUtils.makeAccessible(field);
      ReflectionUtils.setField(field, pageResult, InternalAggregations.EMPTY);
    }
    return pageResult;
  }

}
