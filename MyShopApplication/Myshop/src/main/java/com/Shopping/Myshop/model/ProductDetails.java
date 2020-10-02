package com.Shopping.Myshop.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "ProductDetails")
public class ProductDetails {

    @Id
  //  @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private long productId;

    @Column(name="product_name")
    private String productName;

    @Column(name="product_desc")
    private String productDesc;

    @Column(name="price")
    private long price;

    @Column(name="user_id")
    private long userId;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_path")
    private String filePath;
}
