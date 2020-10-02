package com.Shopping.Myshop.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AddProductRequest {


  //  @JsonProperty("product_id")
    private long productId;

    private String productName;

    private String productDesc;

    private long price;

    private long userId;
}
