package com.Shopping.Myshop.service;


import com.Shopping.Myshop.dto.AddProductRequest;
import com.Shopping.Myshop.model.ProductDetails;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface ProductService {
    public void addProduct(AddProductRequest request);

    public void deleteProduct(long product_id);

    public List<ProductDetails> getProducts();

    public void uploadProduct(MultipartFile file, String productName, String productDesc, long price, long userId, long productId);

}
