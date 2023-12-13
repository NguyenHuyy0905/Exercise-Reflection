package com.nvhuy.dao.repository;


import com.nvhuy.dao.JpaRepositoryClone.JpaRepositoryCloneImpl;
import com.nvhuy.model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository extends JpaRepositoryCloneImpl<Product, Long> {
    public ProductRepository() {
        super(Product.class);
    }
    @Override
    protected List<Product> rowMapper(ResultSet rs) {
        List<Product> productList = new ArrayList<>();
        try {
            while (rs.next()){
                productList.add(Product.builder()
                        .id(rs.getLong("id"))
                        .name(rs.getString("name"))
                        .price(rs.getDouble("price"))
                        .createAt(rs.getDate("createAt"))
                        .build());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productList;
    }
}
