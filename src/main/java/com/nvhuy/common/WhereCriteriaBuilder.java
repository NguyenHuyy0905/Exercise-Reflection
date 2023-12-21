package com.nvhuy.common;

import java.util.ArrayList;
import java.util.List;

public class WhereCriteriaBuilder {
    private List<String> conditions;
    private int limit;
    private int offset;

    public WhereCriteriaBuilder() {
        this.conditions = new ArrayList<>();
        this.limit = -1; // -1 là giá trị mặc định cho việc không có giới hạn
        this.offset = -1; // -1 là giá trị mặc định cho việc không có offset
    }

    public WhereCriteriaBuilder equal(String column, Object value) {
        conditions.add(column + " = '" + value + "'");
        return this;
    }

    public WhereCriteriaBuilder greaterThan(String column, Object value) {
        conditions.add(column + " > '" + value + "'");
        return this;
    }

    public WhereCriteriaBuilder greaterThanOrEqual(String column, Object value) {
        conditions.add(column + " >= '" + value + "'");
        return this;
    }

    public WhereCriteriaBuilder lessThan(String column, Object value) {
        conditions.add(column + " < '" + value + "'");
        return this;
    }

    public WhereCriteriaBuilder lessThanOrEqual(String column, Object value) {
        conditions.add(column + " <= '" + value + "'");
        return this;
    }

    public WhereCriteriaBuilder in(String column, List<Object> values) {
        StringBuilder inCondition = new StringBuilder(column + " IN (");
        for (Object value : values) {
            inCondition.append("'").append(value).append("',");
        }
        // Remove the last comma
        inCondition.setLength(inCondition.length() - 1);
        inCondition.append(")");
        conditions.add(inCondition.toString());
        return this;
    }

    public WhereCriteriaBuilder limit(int limit) {
        this.limit = limit;
        return this;
    }

    public WhereCriteriaBuilder offset(int offset) {
        this.offset = offset;
        return this;
    }

    public String build() {
        if (conditions.isEmpty()) {
            return "";
        }

        StringBuilder whereClause = new StringBuilder("WHERE ");
        for (String condition : conditions) {
            whereClause.append(condition).append(" AND ");
        }

        // Remove the last " AND " from the where clause
        whereClause.setLength(whereClause.length() - 5);

        if (limit > 0) {
            whereClause.append(" LIMIT ").append(limit);
        }

        if (offset > 0) {
            whereClause.append(" OFFSET ").append(offset);
        }

        return whereClause.toString();
    }


}