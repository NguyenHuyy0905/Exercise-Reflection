package org.example.common.consts;

public enum StringSql {
    SELECT_CLAUSE("SELECT * FROM"),
    INSERT_INTO_CLAUSE("INSERT INTO"),
    DELETE_CLAUSE("DELETE FROM"),
    UPDATE_CLAUSE("UPDATE"),
    SET("SET"),
    VALUES("VALUES"),
    WHERE("WHERE"),
    EQUAL("="),
    COMMA(","),
    OPEN_PARENTHESIS("("),
    CLOSE_PARENTHESIS(")"),
    SPACE(" "),
    QUESTION_MARK("?"),
    SEMI_COLON(";"),
    LIMIT("LIMIT"),
    OFFSET("OFFSET")
    ;
    public final String val;
    private  StringSql(String val) {
        this.val = val;
    }
}