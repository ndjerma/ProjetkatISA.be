package com.example.demo.services;

import com.example.demo.models.ProductModel;

import java.util.List;

public interface IProductService {

    List<ProductModel> findAll();
    ProductModel findByTitle(String title);
    ProductModel create(ProductModel productModel);
    ProductModel update(ProductModel productModel);
    void delete(Integer productId);
}
