package com.laegler.microservice.elasticsearch.rest;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.laegler.microservice.elasticsearch.service.ProductIndexService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = {"Product Search Index Service"})
@RestController
@RequestMapping(value = "/products/index")
public class ProductIndexRest {

  private static final Logger LOG = LoggerFactory.getLogger(ProductIndexRest.class);

  @Autowired
  private ProductIndexService indexService;

  @ApiOperation(response = String.class, value = "Trigger reindexing")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
      @ApiResponse(code = 500, message = "Internal server error")})
  @PutMapping(produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<Message> indexPut() {
    LOG.debug("indexPut() called");

    indexService.doIndexing(true);

    return ResponseEntity.ok(new Message("200.1", "Successfully triggered reindexing"));
  }

}
