package com.laegler.microservice.elasticsearch.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.laegler.microservice.elasticsearch.model.Product;

@Repository
public interface ProductSearchRepository
    extends ElasticsearchRepository<Product, Long>, PagingAndSortingRepository<Product, Long> {

  // Page<Article> findByAuthorsName(String name, Pageable pageable);

  // @Query("{\"bool\": {\"must\": [{\"match\": {\"authors.name\": \"?0\"}}]}}")
  // Page<Article> findByAuthorsNameUsingCustomQuery(String name, Pageable pageable);

  // @Query("{\"bool\": {\"must\": [{\"match\": {\"itemCode\": \"?0\"}}]}}")
  // public Page<ProductSearch> findByItemCode(String itemCode);

}
