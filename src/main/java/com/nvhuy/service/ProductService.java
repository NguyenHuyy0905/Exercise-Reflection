package com.nvhuy.service;

import com.nvhuy.model.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Long id);
    List<Product> getAllProducts();
    List<Product> getAllProducts(int limit, int offset);
    Product saveProduct(Product obj);
    Product updateProduct(Long id, Product obj);
    List<Product> saveAllProducts(List<Product> list);
    void deleteProductById(Long id);

    List<Product> getProductsWithConditions(String whereClause);
}
