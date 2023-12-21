package com.nvhuy.service.impliment;

import com.nvhuy.service.ProductService;
import com.nvhuy.dao.repository.ProductRepository;
import com.nvhuy.model.Product;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    public ProductServiceImpl() {
        this.productRepository = new ProductRepository();
    }
    @Override
    public Product getProductById(Long id) {
        return productRepository.getById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.getAll();
    }

    @Override
    public List<Product> getAllProducts(int limit, int offset) {
        return productRepository.getAll(limit, offset);
    }

    @Override
    public Product saveProduct(Product obj) {
        return productRepository.save(obj);
    }

    @Override
    public Product updateProduct(Long id, Product obj) {
        return productRepository.update(id, obj);
    }

    @Override
    public List<Product> saveAllProducts(List<Product> list) {
        return productRepository.saveAll(list);
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getProductsWithConditions(String whereClause) {
        return productRepository.getWithConditions(whereClause);
    }
}
