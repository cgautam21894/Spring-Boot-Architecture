package com.Shopping.Myshop.repository;


import com.Shopping.Myshop.model.ProductDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<ProductDetails, Long> {

}
