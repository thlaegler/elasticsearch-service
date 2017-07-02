package com.laegler.microservice.elasticsearch.elasticsearch;

import lombok.Data;

@Data
public class ProductSearchRequest {

  private String queryString;

  private String manufacturer;

  private String category;
}
