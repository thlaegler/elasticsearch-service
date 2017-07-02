package com.laegler.microservice.elasticsearch.rest;

import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.laegler.microservice.elasticsearch.model.Product;
import com.laegler.microservice.elasticsearch.service.ProductService;

import feature.AbstractProductTestLib;

/**
 * @see ProductSearchIndexRestController
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public class ProductSearchRestSpringTest extends AbstractProductTestLib {

  private static final Logger LOG = LoggerFactory.getLogger(ProductSearchRestSpringTest.class);


  private Product product;

  @Autowired
  private ProductService productService;

  @Autowired
  private ElasticsearchTemplate esTemplate;

  @Before
  public void before() {
    esTemplate.deleteIndex(Product.class);
    esTemplate.createIndex(Product.class);
    esTemplate.putMapping(Product.class);
    esTemplate.refresh(Product.class);

    initWorld("/products");
  }

  @Test
  public void searchGet_shouldFindIndexedProduct() {
    productSearchGet("blue").then().body("[0].id", equalTo(productId));
  }

  @Test
  public void reindexPut_shouldIndexChangedProduct() {
    modifyProductInDatabase(productId, "yellow");
    productSearchGet("yellow").then().body("[0].id", equalTo(productId));
    modifyProductInDatabase(productId, "blue");
  }

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
