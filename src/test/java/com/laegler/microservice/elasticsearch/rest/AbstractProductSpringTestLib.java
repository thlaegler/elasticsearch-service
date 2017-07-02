package com.laegler.microservice.elasticsearch.rest;

import org.springframework.beans.factory.annotation.Autowired;

import com.laegler.microservice.elasticsearch.model.Product;
import com.laegler.microservice.elasticsearch.service.ProductService;

import feature.AbstractProductTestLib;

public abstract class AbstractProductSpringTestLib extends AbstractProductTestLib {

  @Autowired
  private ProductService productService;

  @Override
  protected Product getProductFromDatabase(String productId) {
    return productService.getProduct(Long.parseLong(productId));
  }

  @Override
  protected void modifyProductInDatabase(String productId, String name) {
    product = productService.getProduct(Long.parseLong(productId));
    product.setName(name + " shirt");
    product.setDescription(name);
    productService.saveProduct(product);
  }

}
