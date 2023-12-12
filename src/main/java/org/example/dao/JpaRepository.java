package org.example.dao;

public interface JpaRepository<T, E> {
    T save(T obj);

}
