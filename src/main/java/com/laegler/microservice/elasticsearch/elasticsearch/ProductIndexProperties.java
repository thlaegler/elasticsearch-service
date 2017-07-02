package com.laegler.microservice.elasticsearch.elasticsearch;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "it.elasticsearch.product")
public class ProductIndexProperties {

  private String indexName; // product_search
  private String indexType; // ProductSearch
}
