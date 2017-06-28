package com.laegler.microservice.elasticsearch.service;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

public class ProductEsService {

  protected QueryBuilder createQueryBuilder(String queryString) {
    return QueryBuilders.queryStringQuery(queryString);
  }

  protected SearchQuery createSearchQuery(String queryString) {
    NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

    return queryBuilder.withQuery(createQueryBuilder(queryString)).build();
    // return new IndexQueryBuilder().withIndexName(indexName).wibuild();
  }

  protected SearchQuery createTermQuery(String queryString) {
    NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

    return queryBuilder.withQuery(QueryBuilders.termQuery("*", queryString)).build();
    // return new IndexQueryBuilder().withIndexName(indexName).wibuild();
  }


}
