package com.nvhuy.utils;

import com.nvhuy.common.annotations.Column;
import com.nvhuy.common.annotations.Id;
import com.nvhuy.common.annotations.Table;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class GetComponent {
    private static String idColumnName;
    public static String getTableName(Class<?> clazz) {
        Table tableAnotation  = (Table) clazz.getAnnotation(Table.class);
        String tableName = clazz.getSimpleName();  // if @table not have tableName value  -> tableName = className
        if(tableAnotation != null){
            tableName = tableAnotation.tableName(); // if @table  have tableName value  -> tableName = tableName of @table
        } else {
            System.err.println("Class not match entity class");
        }
        return tableName;
    }
    public static String getIdColumnName(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            Id idAnnotation = f.getAnnotation(Id.class);
            if (idAnnotation != null) idColumnName = idAnnotation.columnName();
        }
        if (StringUtils.isEmpty(idColumnName)) {
//            throw new Exception();
        }
        return idColumnName;
    };

    public static List<String> getListColumnName(Field[] fields) {
        List<String> listColumnName = new ArrayList<>();
        for (Field f : fields) {
            f.setAccessible(true);
            Column columnAnnotation = f.getAnnotation(Column.class);
            if (columnAnnotation != null) listColumnName.add(columnAnnotation.columnName());
        }
        return listColumnName;
    }
}
