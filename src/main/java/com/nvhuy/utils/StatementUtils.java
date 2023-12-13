package com.nvhuy.utils;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class StatementUtils<T> {
    public void setValues(List<String> listColumnName, PreparedStatement preSt, Field[] fields, T t) throws IllegalAccessException, SQLException {
        for (int index = 1; index <= listColumnName.size(); index++) {
            preSt.setObject(index, fields[index].get(t));
        }
    }
}
