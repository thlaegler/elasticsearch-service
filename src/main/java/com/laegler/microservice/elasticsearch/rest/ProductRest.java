package com.laegler.microservice.elasticsearch.rest;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.laegler.microservice.elasticsearch.model.Product;
import com.laegler.microservice.elasticsearch.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = {"Product Service"})
@RestController
@RequestMapping(value = "/products")
public class ProductRest {

  private static final Logger LOG = LoggerFactory.getLogger(ProductRest.class);

  @Autowired
  private ProductService service;

  @ApiOperation(value = "Search with query string", response = Product.class,
      responseContainer = "List")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
      @ApiResponse(code = 500, message = "Internal server error")})
  @GetMapping(produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Product>> productAllGet() {
    LOG.debug("productAllGet() called");

    return ResponseEntity.ok(service.getAllProducts());
  }

  @ApiOperation(value = "Search with query string", response = Product.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
      @ApiResponse(code = 500, message = "Internal server error")})
  @GetMapping(value = "/{productId:.+}", produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<Product> productGet(
      @PathVariable(name = "productId", required = true) @ApiParam(name = "productId",
          example = "1", required = true) final long productId) {
    LOG.debug("productGet() called");

    return ResponseEntity.ok(service.getProduct(productId));
  }

  @ApiOperation(value = "Search with query string", response = Product.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
      @ApiResponse(code = 500, message = "Internal server error")})
  @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<Product> productPost(@RequestBody(required = true) @ApiParam(
      name = "product", required = true) final Product product) {
    LOG.debug("productPost() called");

    Product productResult = service.saveProduct(product);

    return ResponseEntity.created(URI.create("products/" + productResult.getId()))
        .body(productResult);
  }

  @ApiOperation(value = "Search with query string", response = Product.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
      @ApiResponse(code = 500, message = "Internal server error")})
  @PutMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<Product> productPut(@RequestBody(required = true) @ApiParam(
      name = "product", required = true) final Product product) {
    LOG.debug("productPut() called");

    return ResponseEntity.ok(service.saveProduct(product));
  }

  @ApiOperation(value = "Search with query string", response = Product.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
      @ApiResponse(code = 500, message = "Internal server error")})
  @DeleteMapping(value = "/{productId:.+}", produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<Product> productDelete(
      @PathVariable(name = "productId", required = true) @ApiParam(name = "productId",
          example = "1", required = true) final long productId) {
    LOG.debug("productGet() called");

    service.deleteProduct(productId);

    return ResponseEntity.noContent().build();
  }

}
