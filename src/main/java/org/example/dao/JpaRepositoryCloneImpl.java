package org.example.dao;

import org.apache.commons.lang3.StringUtils;
import org.example.common.annotations.Column;
import org.example.common.annotations.Id;
import org.example.common.annotations.Table;
import org.example.common.consts.StringSql;
import org.example.config.Datasource;

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
        // set table name
        Table tableAnotation  = (Table) clazz.getAnnotation(Table.class);
        tableName = clazz.getSimpleName();  // if @table not have tableName value  -> tableName = className
        if(tableAnotation != null){
            tableName = tableAnotation.tableName(); // if @table  have tableName value  -> tableName = tableName of @table
        } else {
            System.err.println("Class not match entity class");
        }
        // set id column name
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            Id idAnnotation = f.getAnnotation(Id.class);
            if (idAnnotation != null) this.idColumnName = idAnnotation.columnName();
        }
        if (StringUtils.isEmpty(this.idColumnName)) {
            // throw Exception.
        }
    }

    @Override
    public T getById(ID id) {
        String sql = new StringBuilder(StringSql.SELECT_CLAUSE.val)
                .append(StringSql.SPACE.val)
                .append(tableName)
                .append(StringSql.SPACE.val)
                .append(StringSql.WHERE.val )
                .append(StringSql.SPACE.val)
                .append(idColumnName)
                .append(StringSql.SPACE.val)
                .append(StringSql.EQUAL.val)
                .append(StringSql.SPACE.val)
                .append(StringSql.QUESTION_MARK.val)
                .append(StringSql.SEMI_COLON.val)
                .toString();
        System.err.println(sql);
        Connection conn = Datasource.getConn();
        PreparedStatement preSt;
        try {
            preSt = conn.prepareStatement(sql);
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
        String sql = new StringBuilder(StringSql.SELECT_CLAUSE.val)
                .append(StringSql.SPACE.val)
                .append(tableName)
                .append(StringSql.SPACE.val)
                .append(StringSql.SEMI_COLON.val)
                .toString();
        System.err.println(sql);
        Connection conn = Datasource.getConn();
        PreparedStatement preSt;
        try {
            preSt  = conn.prepareStatement(sql);
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
        String sql = new StringBuilder(StringSql.SELECT_CLAUSE.val)
                .append(StringSql.SPACE.val)
                .append(tableName)
                .append(StringSql.SPACE.val)
                .append(StringSql.LIMIT.val)
                .append(StringSql.SPACE.val)
                .append(StringSql.QUESTION_MARK.val)
                .append(StringSql.SPACE.val)
                .append(StringSql.OFFSET.val)
                .append(StringSql.SPACE.val)
                .append(StringSql.QUESTION_MARK.val)
                .append(StringSql.SEMI_COLON.val)
                .toString();
        System.err.println(sql);
        Connection conn = Datasource.getConn();
        PreparedStatement preSt;
        try {
            preSt = conn.prepareStatement(sql);
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
        List<String> listColumnName = new ArrayList<>();
        Class<?> clazz = t.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            Column columnAnnotation = f.getAnnotation(Column.class);
            if (columnAnnotation != null) listColumnName.add(columnAnnotation.columnName());
        }
        StringBuilder sql = new StringBuilder()
                .append(StringSql.INSERT_INTO_CLAUSE.val)
                .append(StringSql.SPACE.val)
                .append(tableName)
                .append(StringSql.SPACE.val)
                .append(StringSql.OPEN_PARENTHESIS.val);
        int count = 0;
        for (String s : listColumnName) {
            if (count != 0) {
                sql.append(", ").append(s);
            } else {
                sql.append(s);
            }
            count++;
        }
        sql.append(StringSql.CLOSE_PARENTHESIS.val)
                .append(StringSql.SPACE.val)
                .append(StringSql.VALUES.val)
                .append(StringSql.SPACE.val)
                .append(StringSql.OPEN_PARENTHESIS.val);
        count = 0;
        for (String s : listColumnName) {
            if (count != 0) {
                sql.append(", ?");
            } else {
                sql.append("?");
            }
            count++;
        }
        sql.append(StringSql.CLOSE_PARENTHESIS.val)
                .append(StringSql.SEMI_COLON.val);
        String SQL = sql.toString();
        System.err.println(SQL);
        Connection conn = Datasource.getConn();
        PreparedStatement preSt;
        try {
            preSt = conn.prepareStatement(SQL);
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
        List<String> listColumnName = new ArrayList<>();

        T element = list.get(0);
        Class<?> clazz = element.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            Column columnAnnotation = f.getAnnotation(Column.class);
            if (columnAnnotation != null) listColumnName.add(columnAnnotation.columnName());
        }
        StringBuilder sql = new StringBuilder()
                .append(StringSql.INSERT_INTO_CLAUSE.val)
                .append(StringSql.SPACE.val)
                .append(tableName)
                .append(StringSql.SPACE.val)
                .append(StringSql.OPEN_PARENTHESIS.val);
        int count = 0;
        for (String s : listColumnName) {
            if (count != 0) {
                sql.append(", ").append(s);
            } else {
                sql.append(s);
            }
            count++;
        }
        sql.append(StringSql.CLOSE_PARENTHESIS.val)
                .append(StringSql.SPACE.val)
                .append(StringSql.VALUES.val)
                .append(StringSql.SPACE.val)
                .append(StringSql.OPEN_PARENTHESIS.val);
        count = 0;
        for (String s : listColumnName) {
            if (count != 0) {
                sql.append(", ?");
            } else {
                sql.append("?");
            }
            count++;
        }
        sql.append(StringSql.CLOSE_PARENTHESIS.val)
                .append(StringSql.SEMI_COLON.val);
        String SQL = sql.toString();
//        System.err.println(SQL);
        Connection conn = Datasource.getConn();
        PreparedStatement preSt;
        try {
            preSt = conn.prepareStatement(SQL);
            conn.setAutoCommit(false);
            for (T t : list) {
//                Class<?> eachClazz = t.getClass();
//                Field[] newFields = eachClazz.getDeclaredFields();
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
        List<String> listColumnName = new ArrayList<>();
        Class<?> clazz = t.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            Column columnAnnotation = f.getAnnotation(Column.class);
            if (columnAnnotation != null) listColumnName.add(columnAnnotation.columnName());
        }
        StringBuilder sql = new StringBuilder()
                .append(StringSql.UPDATE_CLAUSE.val)
                .append(StringSql.SPACE.val)
                .append(tableName)
                .append(StringSql.SPACE.val)
                .append(StringSql.SET.val)
                .append(StringSql.SPACE.val);
        int count = 0;
        for (int index = 0; index < listColumnName.size(); index++) {
            if (count != 0) {
                sql.append(", ")
                        .append(listColumnName.get(index))
                        .append(" = ?");
            } else {
                sql.append(listColumnName.get(index))
                        .append(" = ?");
            }
            count++;
        }
        sql.append(StringSql.SPACE.val)
                .append(StringSql.WHERE.val)
                .append(StringSql.SPACE.val)
                .append(idColumnName)
                .append(StringSql.SPACE.val)
                .append(StringSql.EQUAL.val)
                .append(StringSql.SPACE.val)
                .append(StringSql.QUESTION_MARK.val)
                .append(StringSql.SPACE.val)
                .append(StringSql.SEMI_COLON.val);
        String SQL = sql.toString();
        System.err.println(SQL);
        Connection conn = Datasource.getConn();
        PreparedStatement preSt;
        try {
            preSt = conn.prepareStatement(SQL);
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
        String SQL = new StringBuilder(StringSql.DELETE_CLAUSE.val)
                .append(StringSql.SPACE.val)
                .append(tableName)
                .append(StringSql.SPACE.val)
                .append(StringSql.WHERE.val)
                .append(StringSql.SPACE.val)
                .append(idColumnName)
                .append(StringSql.SPACE.val)
                .append(StringSql.EQUAL.val)
                .append(StringSql.SPACE.val)
                .append(StringSql.QUESTION_MARK.val)
                .append(StringSql.SEMI_COLON.val)
                .toString();
        System.err.println(SQL);
        Connection conn = Datasource.getConn();
        PreparedStatement preSt;
        try {
            preSt = conn.prepareStatement(SQL);
            preSt.setObject(1, id);
            preSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
