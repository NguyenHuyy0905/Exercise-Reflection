package com.nvhuy.dao.JpaRepositoryClone;

import java.util.List;

public interface JpaRepositoryClone<T, ID> {
    T getById(ID id);
    List<T> getAll();
    List<T> getAll(int limit, int offset);
    T save(T obj);
    T update(ID id, T obj);
    List<T> saveAll(List<T> list);
    void deleteById(ID id);
    List<T> getWithConditions(String whereClause);

}
