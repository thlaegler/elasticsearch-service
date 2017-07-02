package com.laegler.microservice.elasticsearch.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.InnerField;
import org.springframework.data.elasticsearch.annotations.MultiField;
import org.springframework.data.elasticsearch.annotations.Setting;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Entity
@Table
@Document(indexName = "searchindex", type = "Product")
// @Setting
@Setting(settingPath = "/searchindex-settings.json")
// @Mapping(mappingPath = "/searchindex-mappings.json")
public class Product {

  @ApiModelProperty(example = "1")
  @Field(type = FieldType.Long)
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @ApiModelProperty(example = "Green T-Shirt")
  @Field(type = FieldType.String)
  // , analyzer = Analyzer.SYNONYMS)
  @Column
  private String name;

  @ApiModelProperty(example = "This is a nice product")
  @Field(type = FieldType.String)
  @Column
  private String description;

  @ApiModelProperty(example = "Gucci")
  @Field(type = FieldType.String)
  @Column
  private String manufacturer;

  @ApiModelProperty(example = "1234")
  @Field(type = FieldType.String)
  @Column
  private String itemCode;

  @Field(type = FieldType.Nested)
  @OneToOne
  private Price price;

  @MultiField(mainField = @Field(type = FieldType.Nested),
      otherFields = @InnerField(type = FieldType.Nested, suffix = "test"))
  @Column
  @ManyToMany(fetch = FetchType.EAGER, targetEntity = Category.class)
  private List<Category> categories = new ArrayList<>();

  /**
   * Transient dynamic field
   */
  @ApiModelProperty(example = "Designer, Shirt, Quality")
  @Field(type = FieldType.String)
  @Transient
  private String keywords;

}
