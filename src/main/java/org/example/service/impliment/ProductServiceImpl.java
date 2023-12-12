package org.example.service.impliment;

import org.example.dao.repository.ProductRepository;
import org.example.model.Product;

import java.util.List;

public class ProductServiceImpl implements service.ProductService {
    private ProductRepository productRepository;
    public ProductServiceImpl() {
        this.productRepository = new ProductRepository();
    }
    @Override
    public Product getById(Long id) {
        return productRepository.getById(id);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.getAll();
    }

    @Override
    public List<Product> getAll(int limit, int offset) {
        return productRepository.getAll(limit, offset);
    }

    @Override
    public Product save(Product obj) {
        return productRepository.save(obj);
    }

    @Override
    public Product update(Long id, Product obj) {
        return productRepository.update(id, obj);
    }

    @Override
    public List<Product> saveAll(List<Product> list) {
        return productRepository.saveAll(list);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
