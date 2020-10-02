package com.Shopping.Myshop.service.impl;

import com.Shopping.Myshop.dto.AddProductRequest;
import com.Shopping.Myshop.model.ProductDetails;
import com.Shopping.Myshop.repository.ProductRepository;
import com.Shopping.Myshop.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;

    @Override
    public void addProduct(AddProductRequest request) {

        System.out.println("inside service");
        ProductDetails productDetails = new ProductDetails();
        productDetails.setProductId(request.getProductId());
        productDetails.setPrice(request.getPrice());
        productDetails.setProductDesc(request.getProductDesc());
        productDetails.setProductName(request.getProductName());
        productDetails.setUserId(request.getUserId());
        System.out.println(request);
        productRepository.save(productDetails);
    }

    @Override
    public void deleteProduct(long productId) {
        System.out.println("Product ID is deleted" + productId);
        productRepository.deleteById(productId);
    }

    @Override
    public List<ProductDetails> getProducts() {
        List<ProductDetails> productList = new ArrayList<ProductDetails>();
        System.out.println("All product displayed");
        Iterator<ProductDetails> i = productRepository.findAll().iterator();

        while (i.hasNext()) {
            productList.add(i.next());
        }
        return productList;

    }

    @Override
    public void uploadProduct(MultipartFile file, String productName, String productDesc, long price, long userId, long productId) {

        // To Create Directory
        File dir = new File("E:\\Workspace\\fileupload\\");
        if (!dir.exists()) {
            dir.mkdir();
        }
        //Copy file from POSTMAN to local directory | file.getInputStream() for reading file from request
        try {
            Files.copy(file.getInputStream(), Paths.get("E:\\Workspace\\fileupload\\" + +System.currentTimeMillis()+"_"+file.getOriginalFilename()));
        } catch (IOException e) {
           throw new RuntimeException("FIle is already exist");
        }

        String filepath = String.valueOf(Paths.get("E:\\Workspace\\fileupload\\"+System.currentTimeMillis()+"_"+ file.getOriginalFilename()));
        ProductDetails productDetails = new ProductDetails();
        productDetails.setProductId(productId);
        productDetails.setPrice(price);
        productDetails.setProductDesc(productDesc);
        productDetails.setProductName(productName);
        productDetails.setUserId(userId);
        productDetails.setFileName(file.getOriginalFilename());
        productDetails.setFilePath(filepath);
        productRepository.save(productDetails);

    }

}
