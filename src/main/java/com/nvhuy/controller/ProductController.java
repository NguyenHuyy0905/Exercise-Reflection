package com.nvhuy.controller;

import com.nvhuy.service.ProductService;
import com.nvhuy.service.impliment.ProductServiceImpl;

public class ProductController {
    private final ProductService productService;
    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }
}
