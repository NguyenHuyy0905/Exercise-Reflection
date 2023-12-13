package com.nvhuy.common.annotations;

import com.nvhuy.common.consts.DataType;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
    String columnName();
    DataType type();
}
