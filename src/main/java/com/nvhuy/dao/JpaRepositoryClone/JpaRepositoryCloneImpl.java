package com.nvhuy.dao.JpaRepositoryClone;

import com.nvhuy.config.Datasource;
import com.nvhuy.config.DbConnection;
import com.nvhuy.utils.GetComponent;
import com.nvhuy.utils.SqlBuilder;
import org.apache.commons.lang3.StringUtils;
import com.nvhuy.common.annotations.Column;
import com.nvhuy.common.annotations.Id;
import com.nvhuy.common.annotations.Table;
import com.nvhuy.common.consts.StringSql;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class JpaRepositoryCloneImpl<T, ID> implements JpaRepositoryClone<T, ID> {
    private final String clazzName;
    private final Class<?> clazz;
    private String tableName;
    private String idColumnName;

    protected abstract List<T> rowMapper(ResultSet rs);
    public JpaRepositoryCloneImpl(Class<?> clazz) {
        this.clazz = clazz;
        this.clazzName = clazz.getSimpleName();
        this.tableName = GetComponent.getTableName(clazz);
        this.idColumnName = GetComponent.getIdColumnName(clazz);
    }

    @Override
    public T getById(ID id) {
        String SQL = SqlBuilder.SqlSelectById(tableName, idColumnName);
        System.err.println(SQL);
//        Connection conn = Datasource.getConn();
//        PreparedStatement preSt;
        try (Connection conn = DbConnection.createConnection();
             PreparedStatement preSt = conn.prepareStatement(SQL)) {
            preSt.setObject(1, id);
            ResultSet rs = preSt.executeQuery();
            List<T> data = rowMapper(rs);
            if(data != null && data.size() > 0){
                return data.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public List<T> getAll(){
        String SQL = SqlBuilder.SqlSelectAll(tableName);
        System.err.println(SQL);
        try (Connection conn = DbConnection.createConnection();
             PreparedStatement preSt = conn.prepareStatement(SQL)) {
            ResultSet rs = preSt.executeQuery();
            List<T> data = rowMapper(rs);
            if(data != null && data.size() > 0){
                return data;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public List<T> getAll(int limit, int offset){
        String SQL = SqlBuilder.SqlSelectAllLimit(tableName);
        System.err.println(SQL);
        try (Connection conn = DbConnection.createConnection();
             PreparedStatement preSt = conn.prepareStatement(SQL)) {
            preSt.setInt(1,limit);
            preSt.setInt(2,offset);
            ResultSet rs = preSt.executeQuery();
            List<T> data = rowMapper(rs);
            if(data != null && data.size() > 0){
                return data;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    };
    public T save(T t) {
        Class<?> clazz = t.getClass();
        Field[] fields = clazz.getDeclaredFields();
        List<String> listColumnName = GetComponent.getListColumnName(fields);
        String SQL = SqlBuilder.SqlInsert(tableName, listColumnName);
        System.err.println(SQL);
        try (Connection conn = DbConnection.createConnection();
             PreparedStatement preSt = conn.prepareStatement(SQL)) {
            conn.setAutoCommit(false);
            for (int index = 1; index <= listColumnName.size(); index++) {
                preSt.setObject(index, fields[index].get(t));
            }
            preSt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return t;
    }

    @Override
    public List<T> saveAll(List<T> list) {
        T element = list.get(0);
        Class<?> clazz = element.getClass();
        Field[] fields = clazz.getDeclaredFields();
        List<String> listColumnName = GetComponent.getListColumnName(fields);
        String SQL = SqlBuilder.SqlInsert(tableName, listColumnName);
        System.err.println(SQL);

        try (Connection conn = DbConnection.createConnection();
             PreparedStatement preSt = conn.prepareStatement(SQL)) {
            conn.setAutoCommit(false);
            for (T t : list) {
                for (int index = 1; index <= listColumnName.size(); index++) {
                    preSt.setObject(index, fields[index].get(t));
                }
                preSt.addBatch();
            }
            preSt.executeBatch();
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public T update(ID id, T t) {
        Class<?> clazz = t.getClass();
        Field[] fields = clazz.getDeclaredFields();
        List<String> listColumnName = GetComponent.getListColumnName(fields);
        String SQL = SqlBuilder.SqlUpdate(tableName, idColumnName, listColumnName);
        System.err.println(SQL);

        try (Connection conn = DbConnection.createConnection();
             PreparedStatement preSt = conn.prepareStatement(SQL)) {
            conn.setAutoCommit(false);
            for (int index = 1; index <= listColumnName.size(); index++) {
                preSt.setObject(index, fields[index].get(t));
            }
            preSt.setObject(listColumnName.size() + 1, id);
            preSt.executeUpdate();
            conn.commit();
        }  catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return t;
    };
    @Override
    public void deleteById(ID id) {
        String SQL = SqlBuilder.SqlDelete(tableName, idColumnName);
        System.err.println(SQL);
        try (Connection conn = DbConnection.createConnection();
             PreparedStatement preSt = conn.prepareStatement(SQL)) {
            preSt.setObject(1, id);
            preSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
