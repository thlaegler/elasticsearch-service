package com.laegler.microservice.elasticsearch.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.laegler.microservice.elasticsearch.model.Product;
import com.laegler.microservice.elasticsearch.repo.ProductJpaRepository;

@Service
public class ProductService {

  private static final Logger LOG = LoggerFactory.getLogger(ProductService.class);

  @Autowired
  private ProductJpaRepository jpaRepository;

  public Page<Product> getAllProductsPaged() {
    return jpaRepository.findAll(new PageRequest(0, 20));
  }

  public List<Product> getAllProducts() {
    return (List<Product>) jpaRepository.findAll();
  }

  public Product getProduct(final long productId) {
    return jpaRepository.findOne(productId);
  }

  public Product saveProduct(final Product product) {
    return jpaRepository.save(product);
  }

  public void deleteProduct(final long productId) {
    jpaRepository.delete(productId);
  }

}
