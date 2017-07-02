package com.laegler.microservice.elasticsearch.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Entity
@Table
@Document(indexName = "searchindex", type = "Category")
public class Category {

  @ApiModelProperty(example = "1")
  @Field(type = FieldType.Long)
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @ApiModelProperty(example = "T-Shirts")
  @Field(type = FieldType.String)
  @Column
  private String metaTitle;

  @ApiModelProperty(example = "All kinds of T-Shirts in all variations")
  @Field(type = FieldType.String)
  @Column
  private String metaDescription;

  @ApiModelProperty(example = "shirt, sweater, pullover")
  @Field(type = FieldType.String)
  @Column
  private String metaKeywords;

  @ApiModelProperty(example = "All kinds of T-Shirts")
  @Field(type = FieldType.String)
  @Column
  private String description;

  @ApiModelProperty(example = "T-Shirts")
  @Field(type = FieldType.String)
  @Column
  private String name;

  // @ApiModelProperty
  // @Field(type = FieldType.Nested)
  // @Column
  // @ManyToMany(fetch = FetchType.LAZY, targetEntity = Product.class)
  // private List<Product> products = new ArrayList<>();

}
