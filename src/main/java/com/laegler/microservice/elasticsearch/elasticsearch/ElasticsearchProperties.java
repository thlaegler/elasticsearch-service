package com.laegler.microservice.elasticsearch.elasticsearch;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "spring.data.elasticsearch")
public class ElasticsearchProperties {

  private String clusterName; // microservice-cluster
  private String clusterNodes; // microservice-es:9300
  private String host; // microservice-es
  private int port; // 9300
  private Map<String, Object> properties;
}
