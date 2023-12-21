package com.nvhuy.test;

import com.nvhuy.common.WhereCriteriaBuilder;
import com.nvhuy.model.Product;
import com.nvhuy.service.impliment.ProductServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class ProductTest {
    public static void main(String[] args) {
        ProductServiceImpl service = new ProductServiceImpl();
//        List<Product> productList = service.getAllProducts();
//        System.err.println(productList);
//        Product product = service.getProductById(56L);
//        System.err.println(product);
//
//        Product productSave = new Product("iphone11", 1000);
//        service.saveProduct(productSave);
//        List<Product> list = new ArrayList<>();
//        list.add(new Product("iphoneX1", 2000));
//        list.add(new Product("iphoneX2", 2000));
//        service.saveAllProducts(list);
//        Product updateProduct = new Product("Samsung", 1200);
//        service.updateProduct(40L, updateProduct);
//        Long id = 45L;
//        service.deleteProductById(id);
//        List<Product> productListLimit = service.getAllProducts(3, 3);
//        System.err.println(productListLimit);


        WhereCriteriaBuilder builder = new WhereCriteriaBuilder();

        builder.equal("name", "iphoneX1")
//                .greaterThan("price", 1000);
                .in("price", List.of(1000, 1200, 2000));
//                .limit(10)
//                .offset(20);

        String whereClause = builder.build();

        List<Product> productList = service.getProductsWithConditions(whereClause);
        System.out.println(productList);
    }
}
