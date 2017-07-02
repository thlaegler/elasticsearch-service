package com.laegler.microservice.elasticsearch.rest;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.elasticsearch.search.aggregations.Aggregations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.laegler.microservice.elasticsearch.service.ProductAggregationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = {"Product Search Aggregation Service"})
@RestController
@RequestMapping(value = "/products/aggregation")
public class ProductAggregationRest {

  private static final Logger LOG = LoggerFactory.getLogger(ProductAggregationRest.class);

  @Autowired
  private ProductAggregationService service;

  @ApiOperation(value = "Search aggregations with query string", response = Aggregations.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
      @ApiResponse(code = 500, message = "Internal server error")})
  @GetMapping(value = "/{queryString:.+}", produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<Aggregations> aggregationsGet(
      @PathVariable(name = "queryString", required = true) @ApiParam(name = "queryString",
          example = "The product I want", required = true) String queryString) {
    LOG.debug("indexGet() called");

    return ResponseEntity.ok(service.getAggregations(queryString));
  }

}
