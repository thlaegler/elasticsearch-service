package com.laegler.microservice.elasticsearch.rest;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Message {

  @ApiModelProperty(example = "200.1")
  private String id;

  @ApiModelProperty(example = "Successfully created resource XZ")
  private String body;

  @ApiModelProperty(example = "INFO")
  private MessageSeverity severity;

  @ApiModelProperty(example = "INFO")
  private Throwable throwable;

  @ApiModelProperty(example = "Here is the link to your resource: www...")
  private String description;

  public Message(String id, String body) {
    super();
    this.id = id;
    this.body = body;
    this.severity = MessageSeverity.INFO;
  }

  public Message(String id, String body, Throwable throwable) {
    super();
    this.id = id;
    this.body = body;
    this.throwable = throwable;
    this.severity = MessageSeverity.ERROR;
  }

  public Message(String id, String body, MessageSeverity severity) {
    super();
    this.id = id;
    this.body = body;
    this.severity = severity;
  }

  public Message(String id, String body, MessageSeverity severity, String description) {
    super();
    this.id = id;
    this.body = body;
    this.severity = severity;
    this.description = description;
  }

}
