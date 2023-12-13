package com.nvhuy.common.consts;

public enum DataType {
    TEXT("TEXT"),
    VARCHAR("VARCHAR"),
    INT("INT"),
    LONG("BIGINT"),
    DATE("DATE"),
    DOUBLE("DOUBLE")
    ;
    public final String val;
    private  DataType(String val) {
        this.val = val;
    }
}
