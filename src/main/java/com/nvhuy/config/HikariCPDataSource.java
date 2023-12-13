package com.nvhuy.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class HikariCPDataSource {

    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    static {
        config.setJdbcUrl("jdbc:mysql://localhost:3306/t2303e");
        config.setUsername("root");
        config.setPassword("");
        ds = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    public static void main(String[] args) throws SQLException {
        Connection conn = getConnection();
        if(conn != null) System.err.println("Connection ok");
    }
    private HikariCPDataSource(){}
}
