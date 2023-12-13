package com.nvhuy.utils;

import com.nvhuy.common.consts.StringSql;

import java.util.List;

public class SqlBuilder {
    public static String SqlInsert(String tableName, List<String> listColumnName) {
        StringBuilder sql = new StringBuilder().append(StringSql.INSERT_INTO_CLAUSE.val)
                .append(StringSql.SPACE.val).append(tableName)
                .append(StringSql.SPACE.val).append(StringSql.OPEN_PARENTHESIS.val);
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
                .append(StringSql.SPACE.val).append(StringSql.VALUES.val)
                .append(StringSql.SPACE.val).append(StringSql.OPEN_PARENTHESIS.val);
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
        return SQL;
    }
    public static String SqlUpdate(String tableName, String idColumnName, List<String> listColumnName) {
        StringBuilder sql = new StringBuilder().append(StringSql.UPDATE_CLAUSE.val)
                .append(StringSql.SPACE.val).append(tableName)
                .append(StringSql.SPACE.val).append(StringSql.SET.val)
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
        sql.append(StringSql.SPACE.val).append(StringSql.WHERE.val)
                .append(StringSql.SPACE.val).append(idColumnName)
                .append(StringSql.SPACE.val).append(StringSql.EQUAL.val)
                .append(StringSql.SPACE.val).append(StringSql.QUESTION_MARK.val)
                .append(StringSql.SPACE.val).append(StringSql.SEMI_COLON.val);
        String SQL = sql.toString();
        return SQL;
    }
    public static String SqlDelete(String tableName, String idColumnName) {
        String SQL = new StringBuilder(StringSql.DELETE_CLAUSE.val)
                .append(StringSql.SPACE.val).append(tableName)
                .append(StringSql.SPACE.val).append(StringSql.WHERE.val)
                .append(StringSql.SPACE.val).append(idColumnName)
                .append(StringSql.SPACE.val).append(StringSql.EQUAL.val)
                .append(StringSql.SPACE.val).append(StringSql.QUESTION_MARK.val)
                .append(StringSql.SEMI_COLON.val)
                .toString();
        return SQL;
    }
    public static String SqlSelectAllLimit(String tableName) {
        String sql = new StringBuilder(StringSql.SELECT_CLAUSE.val)
                .append(StringSql.SPACE.val).append(tableName)
                .append(StringSql.SPACE.val).append(StringSql.LIMIT.val)
                .append(StringSql.SPACE.val).append(StringSql.QUESTION_MARK.val)
                .append(StringSql.SPACE.val).append(StringSql.OFFSET.val)
                .append(StringSql.SPACE.val).append(StringSql.QUESTION_MARK.val)
                .append(StringSql.SEMI_COLON.val)
                .toString();
        return sql;
    }
    public static String SqlSelectAll(String tableName) {
        String sql = new StringBuilder(StringSql.SELECT_CLAUSE.val)
                .append(StringSql.SPACE.val).append(tableName)
                .append(StringSql.SPACE.val).append(StringSql.SEMI_COLON.val)
                .toString();
        return sql;
    }
    public static String SqlSelectById(String tableName, String idColumnName) {
        String sql = new StringBuilder(StringSql.SELECT_CLAUSE.val)
                .append(StringSql.SPACE.val).append(tableName)
                .append(StringSql.SPACE.val).append(StringSql.WHERE.val)
                .append(StringSql.SPACE.val).append(idColumnName)
                .append(StringSql.SPACE.val).append(StringSql.EQUAL.val)
                .append(StringSql.SPACE.val).append(StringSql.QUESTION_MARK.val)
                .append(StringSql.SEMI_COLON.val)
                .toString();
        return sql;
    }
}
