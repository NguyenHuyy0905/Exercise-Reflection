package com.nvhuy.dao.JpaRepositoryClone;

import com.nvhuy.config.DbConnection;
import com.nvhuy.utils.DbGetter;
import com.nvhuy.utils.SqlBuilder;
import com.nvhuy.utils.StatementUtils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.List;

public abstract class JpaRepositoryCloneImpl<T, ID> implements JpaRepositoryClone<T, ID> {
    private final String clazzName;
    private final Class<?> clazz;
    private final Field[] fields;
    private String tableName;
    private String idColumnName;
    private StatementUtils<T> statementUtils;
    private List<String> listColumns;
    protected abstract List<T> rowMapper(ResultSet rs);
    public JpaRepositoryCloneImpl(Class<?> clazz) {
        this.clazz = clazz;
        this.fields = clazz.getDeclaredFields();
        this.clazzName = clazz.getSimpleName();
        this.tableName = DbGetter.getTableName(clazz);
        this.idColumnName = DbGetter.getIdColumnName(clazz);
        this.statementUtils = new StatementUtils<>();
        this.listColumns = DbGetter.getListColumns(fields);
    }

    @Override
    public T getById(ID id) {
        String SQL = SqlBuilder.SqlSelectById(tableName, idColumnName);
        System.err.println(SQL);
        try (Connection conn = DbConnection.createConnection();
             PreparedStatement preSt = conn.prepareStatement(SQL)) {
            conn.setAutoCommit(false);
            preSt.setObject(1, id);
            ResultSet rs = preSt.executeQuery();
            conn.commit();
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
            conn.setAutoCommit(false);
            ResultSet rs = preSt.executeQuery();
            List<T> data = rowMapper(rs);
            if(data != null && data.size() > 0){
                return data;
            }
            conn.commit();
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
            conn.setAutoCommit(false);
            preSt.setInt(1,limit);
            preSt.setInt(2,offset);
            ResultSet rs = preSt.executeQuery();
            conn.commit();
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
        String SQL = SqlBuilder.SqlInsert(tableName, listColumns);
        System.err.println(SQL);
        try (Connection conn = DbConnection.createConnection();
             PreparedStatement preSt = conn.prepareStatement(SQL)) {
            conn.setAutoCommit(false);
            statementUtils.setValues(listColumns, preSt, fields, t);
            preSt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return t;
    }

    @Override
    public List<T> saveAll(List<T> list) {
        String SQL = SqlBuilder.SqlInsert(tableName, listColumns);
        System.err.println(SQL);
        try (Connection conn = DbConnection.createConnection();
             PreparedStatement preSt = conn.prepareStatement(SQL)) {
            conn.setAutoCommit(false);
            for (T t : list) {
                statementUtils.setValues(listColumns, preSt, fields, t);
                preSt.addBatch();
            }
            preSt.executeBatch();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public T update(ID id, T t) {
        String SQL = SqlBuilder.SqlUpdate(tableName, idColumnName, listColumns);
        System.err.println(SQL);
        try (Connection conn = DbConnection.createConnection();
             PreparedStatement preSt = conn.prepareStatement(SQL)) {
            conn.setAutoCommit(false);
            statementUtils.setValues(listColumns, preSt, fields, t);
            preSt.setObject(listColumns.size() + 1, id);
            preSt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
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
            conn.setAutoCommit(false);
            preSt.setObject(1, id);
            preSt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
