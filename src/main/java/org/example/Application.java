package org.example;

import org.example.dao.repository.ProductRepository;
import org.example.model.Product;
import org.example.service.impliment.ProductServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        ProductServiceImpl service = new ProductServiceImpl();
        List<Product> productList = service.getAll();
        System.err.println(productList);
        Product product = service.getById(14L);
        System.err.println(product);
        List<Product> productListLimit = service.getAll(3, 3);
        System.err.println(productListLimit);
        Product productSave = new Product("iphone11", 1000);
        service.save(productSave);
        List<Product> list = new ArrayList<>();
        list.add(new Product("iphoneX1", 2000));
        list.add(new Product("iphoneX2", 2000));
        list.add(new Product("iphoneX3", 2000));
        list.add(new Product("iphoneX4", 2000));
        list.add(new Product("iphoneX5", 2000));
//        service.saveAll(list);
        Product updateProduct = new Product("Samsung", 1500);
        service.update(3L, updateProduct);
        Long id = 5L;
        service.deleteById(id);
    }
}