package com.lagou.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DruidUtils {

    // 1. make the public static datasouce
    public static DataSource dataSource;

    // 2. creaate the datasource using the factory using the static block
    static {

        try {
            Properties properties = new Properties();
            properties.load(DruidUtils.class.getClassLoader().getResourceAsStream("druid.properties"));
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 3. get the connection
    public static Connection getConnection() {

        try {
            return dataSource.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    // the method to get the Druid datasource
    public static DataSource getDataSource() {
        return dataSource;
    }

    public static void close(Connection connection, Statement statement) {

        if (connection != null && statement != null) {
            try {
                statement.close();
                // just return the connection to pool
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Connection connection, Statement statement, ResultSet resultSet) {

        if (connection != null && statement != null && resultSet != null) {
            try {
                resultSet.close();
                statement.close();
                // just return the connection to pool
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
