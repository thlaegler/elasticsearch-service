package com.laegler.microservice.elasticsearch.config;

import java.net.UnknownHostException;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.laegler.microservice.elasticsearch.elasticsearch.ElasticsearchProperties;

@Configuration
@EnableElasticsearchRepositories(
    basePackages = "com.laegler.microservice.elasticsearch.elasticsearch")
@EnableJpaRepositories(basePackages = "com.laegler.microservice.elasticsearch.repo")
public class ElasticsearchConfig {

  @Autowired
  private ElasticsearchProperties props;

  @Bean
  public Node node() {
    return new NodeBuilder().local(true).settings(settings()).node();
  }

  @Bean
  public Client client() throws UnknownHostException {
    // TransportClient client = new
    // TransportClient(elasticsearchSettings());
    // client.addTransportAddress(new
    // InetSocketTransportAddress(InetAddress.getByName(host), port));
    return node().client();
  }

  @Bean
  public ElasticsearchOperations template() throws UnknownHostException {
    return new ElasticsearchTemplate(client());
  }

  @Bean
  public Settings settings() {
    return ImmutableSettings.settingsBuilder().put("cluster.name", props.getClusterName()).build();
  }

}
