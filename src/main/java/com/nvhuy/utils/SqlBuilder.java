package com.nvhuy.utils;

import com.nvhuy.common.consts.StringSql;

import java.util.List;

public class SqlBuilder {
    public static String SqlInsert(String tableName, List<String> listColumnName) {
        return new StringBuilder(StringSql.INSERT_INTO_CLAUSE.val)
                .append(StringSql.SPACE.val).append(tableName)
                .append(StringSql.SPACE.val).append(StringSql.OPEN_PARENTHESIS.val)
                .append(StringSql.SPACE.val).append(appendColumns(listColumnName))
                .append(StringSql.SPACE.val).append(StringSql.CLOSE_PARENTHESIS.val)
                .append(StringSql.SPACE.val).append(StringSql.VALUES.val)
                .append(StringSql.SPACE.val).append(StringSql.OPEN_PARENTHESIS.val)
                .append(StringSql.SPACE.val).append(appendValues(listColumnName))
                .append(StringSql.SPACE.val).append(StringSql.CLOSE_PARENTHESIS.val)
                .append(StringSql.SPACE.val).append(StringSql.SEMI_COLON.val)
                .toString();
    }
    public static String SqlUpdate(String tableName, String idColumnName, List<String> listColumnNameHaveValue) {
        return new StringBuilder(StringSql.UPDATE_CLAUSE.val)
                .append(StringSql.SPACE.val).append(tableName)
                .append(StringSql.SPACE.val).append(StringSql.SET.val)
                .append(StringSql.SPACE.val).append(appendUpdateData(listColumnNameHaveValue))
                .append(StringSql.SPACE.val).append(StringSql.WHERE.val)
                .append(StringSql.SPACE.val).append(idColumnName)
                .append(StringSql.SPACE.val).append(StringSql.EQUAL.val)
                .append(StringSql.SPACE.val).append(StringSql.QUESTION_MARK.val)
                .append(StringSql.SPACE.val).append(StringSql.SEMI_COLON.val)
                .toString();
    }
    public static String SqlDelete(String tableName, String idColumnName) {
        return new StringBuilder(StringSql.DELETE_CLAUSE.val)
                .append(StringSql.SPACE.val).append(tableName)
                .append(StringSql.SPACE.val).append(StringSql.WHERE.val)
                .append(StringSql.SPACE.val).append(idColumnName)
                .append(StringSql.SPACE.val).append(StringSql.EQUAL.val)
                .append(StringSql.SPACE.val).append(StringSql.QUESTION_MARK.val)
                .append(StringSql.SPACE.val).append(StringSql.SEMI_COLON.val)
                .toString();
    }
    public static String SqlSelectAllLimit(String tableName) {
        return new StringBuilder(StringSql.SELECT_CLAUSE.val)
                .append(StringSql.SPACE.val).append(tableName)
                .append(StringSql.SPACE.val).append(StringSql.LIMIT.val)
                .append(StringSql.SPACE.val).append(StringSql.QUESTION_MARK.val)
                .append(StringSql.SPACE.val).append(StringSql.OFFSET.val)
                .append(StringSql.SPACE.val).append(StringSql.QUESTION_MARK.val)
                .append(StringSql.SPACE.val).append(StringSql.SEMI_COLON.val)
                .toString();
    }
    public static String SqlSelectAll(String tableName) {
        return new StringBuilder(StringSql.SELECT_CLAUSE.val)
                .append(StringSql.SPACE.val).append(tableName)
                .append(StringSql.SPACE.val)
                .toString();
    }
    public static String SqlSelectById(String tableName, String idColumnName) {
        return new StringBuilder(StringSql.SELECT_CLAUSE.val)
                .append(StringSql.SPACE.val).append(tableName)
                .append(StringSql.SPACE.val).append(StringSql.WHERE.val)
                .append(StringSql.SPACE.val).append(idColumnName)
                .append(StringSql.SPACE.val).append(StringSql.EQUAL.val)
                .append(StringSql.SPACE.val).append(StringSql.QUESTION_MARK.val)
                .append(StringSql.SPACE.val).append(StringSql.SEMI_COLON.val)
                .toString();
    }

    // ****** Append modules ******
    private static String appendColumns(List<String> listColumnName) {
        StringBuilder sql = new StringBuilder();
        for (int index = 0; index < listColumnName.size(); index++) {
            sql = (index != 0)
                    ? sql.append(", ").append(listColumnName.get(index))
                    : sql.append(listColumnName.get(index));
        }
        return sql.toString();
    }
    private static String appendValues(List<String> listColumnName) {
        StringBuilder sql = new StringBuilder();
        for (int index = 0; index < listColumnName.size(); index++) {
            sql = (index != 0) ? sql.append(", ?") : sql.append("?");
        }
        return sql.toString();
    }
    private static String appendUpdateData(List<String> listColumnName) {
        StringBuilder sql = new StringBuilder();
        for (int index = 0; index < listColumnName.size(); index++) {
            sql = (index != 0)
                    ? sql.append(", ").append(listColumnName.get(index)).append(" = ?")
                    : sql.append(listColumnName.get(index)).append(" = ?");
        }
        return sql.toString();
    }
}
