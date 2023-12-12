package org.example.common.annotations;

import org.example.common.consts.DataType;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
    String columnName();
    DataType type();
}
