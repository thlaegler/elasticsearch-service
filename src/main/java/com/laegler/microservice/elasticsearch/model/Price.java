package com.laegler.microservice.elasticsearch.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
public class Price {

	@ApiModelProperty(example = "1")
	@Field(type = FieldType.Long)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ApiModelProperty(example = "US Dollar")
	@Field(type = FieldType.String)
	@Column
	private String currency;

	@ApiModelProperty(example = "49.99")
	@Field(type = FieldType.Double)
	@Column
	private double amount;

	@ApiModelProperty(example = "16.0")
	@Field(type = FieldType.Double)
	@Column
	private double taxRate;

	@ApiModelProperty(example = "0.0")
	@Field(type = FieldType.Double)
	@Column
	private double discountRate;

}
