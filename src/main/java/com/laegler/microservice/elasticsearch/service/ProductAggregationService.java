package com.laegler.microservice.elasticsearch.service;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductAggregationService extends ProductEsService {

  private static final Logger LOG = LoggerFactory.getLogger(ProductAggregationService.class);

  @Autowired
  private Client esClient;

  @Autowired
  private ElasticsearchTemplate template;

  public Aggregations getAggregations() {
    SearchResponse response = esClient.prepareSearch()
        .addAggregation(AggregationBuilders.nested("test")).execute().actionGet();
    return response.getAggregations();
  }

  public Aggregations getAggregations(String queryString) {
    SearchResponse sr = esClient.prepareSearch().setQuery(createQueryBuilder(queryString))
        .addAggregation(AggregationBuilders.nested("test")).execute().actionGet();
    return sr.getAggregations();
  }

}
