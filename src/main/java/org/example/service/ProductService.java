package service;

import org.example.model.Product;

import java.util.List;

public interface ProductService {
    Product getById(Long id);
    List<Product> getAll();
    List<Product> getAll(int limit, int offset);
    Product save(Product obj);
    Product update(Long id, Product obj);
    List<Product> saveAll(List<Product> list);
    void deleteById(Long id);
}
