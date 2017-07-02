package com.laegler.microservice.elasticsearch.rest;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.laegler.microservice.elasticsearch.elasticsearch.ProductSearchRequest;
import com.laegler.microservice.elasticsearch.model.Product;
import com.laegler.microservice.elasticsearch.service.ProductSearchService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = {"Product Search Service"})
@RestController
@RequestMapping(value = "/products/search")
public class ProductSearchRest {

  private static final Logger LOG = LoggerFactory.getLogger(ProductSearchRest.class);

  @Autowired
  private ProductSearchService service;

  @ApiOperation(value = "Search with query string", response = Product.class,
      responseContainer = "List")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
      @ApiResponse(code = 500, message = "Internal server error")})
  @GetMapping(value = "/{queryString:.+}", produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Product>> searchGet(
      @PathVariable(name = "queryString", required = true) @ApiParam(name = "queryString",
          example = "teh quiry thing", required = true) final String queryString) {
    LOG.debug("indexGet() called");

    return ResponseEntity.ok(service.search(queryString));
  }

  @ApiOperation(value = "Search with search request", response = Product.class,
      responseContainer = "List")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
      @ApiResponse(code = 500, message = "Internal server error")})
  @GetMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Product>> searchRequestGet(@RequestBody(required = true) @ApiParam(
      name = "queryString", required = true) final ProductSearchRequest searchRequest) {
    LOG.debug("searchRequestGet() called");

    return ResponseEntity.ok(service.search(searchRequest));
  }

  @ApiOperation(value = "Search with prepared template", response = Product.class,
      responseContainer = "List")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
      @ApiResponse(code = 500, message = "Internal server error")})
  @GetMapping(value = "/template/{templateId:.+}", produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Product>> templateGet(
      @PathVariable(name = "queryString", required = true) @ApiParam(name = "templateId",
          example = "1", required = true) final long templateId) {
    LOG.debug("templateGet() called");

    // return ResponseEntity.ok(service.search(templateId));
    return ResponseEntity.ok().build();
  }

}
