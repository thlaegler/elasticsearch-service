// package com.laegler.microservice.elasticsearch.elasticsearch;
//
// import java.io.Serializable;
//
// import org.springframework.data.annotation.Id;
// import org.springframework.data.elasticsearch.annotations.Document;
// import org.springframework.data.elasticsearch.annotations.Field;
// import org.springframework.data.elasticsearch.annotations.FieldType;
// import org.springframework.data.elasticsearch.annotations.Setting;
//
// import com.fasterxml.jackson.annotation.JsonCreator;
// import com.fasterxml.jackson.annotation.JsonInclude;
// import com.laegler.microservice.elasticsearch.model.Product;
//
// import io.swagger.annotations.ApiModel;
// import lombok.Data;
// import lombok.NonNull;
//
// @ApiModel
// @JsonInclude(JsonInclude.Include.NON_NULL)
// @Data
// @Document(indexName = "searchindex", type = "Product")
//// @Document(indexName = "searchindex")
// @Setting(settingPath = "/searchindex-settings.json")
//// @Mapping(mappingPath = "/searchindex-mappings.json")
// public class ProductSearchIndex implements Serializable {
//
// // @JsonProperty
// @Id
// private String id;
//
// // @JsonProperty
// @Field(type = FieldType.Nested)
// @NonNull
// private Product product;
//
// @JsonCreator
// public ProductSearchIndex() {}
//
// // @JsonCreator
// public ProductSearchIndex(Product product) {
// this.product = product;
// }
//
// // @JsonCreator
// public ProductSearchIndex(String id, Product product) {
// this.id = id;
// this.product = product;
// }
//
// }
