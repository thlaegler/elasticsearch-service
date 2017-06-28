package com.laegler.microservice.elasticsearch.rest;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.elasticsearch.search.aggregations.Aggregations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.laegler.microservice.elasticsearch.model.Product;
import com.laegler.microservice.elasticsearch.service.ProductAggregationService;
import com.laegler.microservice.elasticsearch.service.ProductIndexService;
import com.laegler.microservice.elasticsearch.service.ProductSearchService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = {"Product Elasticsearch Service"})
@RestController
@RequestMapping(value = "/products/search")
public class ProductEsRestController {

  private static final Logger LOG = LoggerFactory.getLogger(ProductEsRestController.class);

  @Autowired
  private ProductIndexService indexService;

  @Autowired
  private ProductSearchService searchService;

  @Autowired
  private ProductAggregationService aggregationService;

  @ApiOperation(value = "Search with query string", response = Product.class,
      responseContainer = "List")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
      @ApiResponse(code = 500, message = "Internal server error")})
  @GetMapping(value = "/{queryString:.+}", produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Product>> searchGet(
      @PathVariable(name = "queryString", required = true) @ApiParam(name = "queryString",
          example = "The product I want", required = true) String queryString) {
    LOG.debug("indexGet() called");

    return ResponseEntity.ok(searchService.search(queryString));
  }

  @ApiOperation(value = "Search with query string", response = Aggregations.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
      @ApiResponse(code = 500, message = "Internal server error")})
  @GetMapping(value = "/aggregation/{queryString:.+}", produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<Aggregations> aggregationsGet(
      @PathVariable(name = "queryString", required = true) @ApiParam(name = "queryString",
          example = "The product I want", required = true) String queryString) {
    LOG.debug("indexGet() called");

    return ResponseEntity.ok(aggregationService.getAggregations(queryString));
  }

  @ApiOperation(value = "Search with prepared template", response = Product.class,
      responseContainer = "List")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
      @ApiResponse(code = 500, message = "Internal server error")})
  @GetMapping(value = "/template/{templateId:.+}", produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Product>> templateGet(
      @PathVariable(name = "queryString", required = true) @ApiParam(name = "templateId",
          example = "The product I want", required = true) String templateId) {
    LOG.debug("templateGet() called");

    // return ResponseEntity.ok(service.search(templateId));
    return ResponseEntity.ok().build();
  }

  @ApiOperation(response = String.class, value = "Trigger reindexing")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
      @ApiResponse(code = 500, message = "Internal server error")})
  @PostMapping(value = "/reindex}", produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<Message> reindexPost() throws InterruptedException, ExecutionException {
    LOG.debug("reindexPost() called");

    indexService.reindex();

    return ResponseEntity.ok(new Message("200.1", "Successfully triggered reindexing"));
  }

}
