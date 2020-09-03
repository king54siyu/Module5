package com.lagou.app;

import com.lagou.utils.DruidUtils;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class TestPhone {

    /**
     * Here we test:
     * 需求1:  查询价格高于2000元，生产日期是2019年之前的所有手机
     * 需求2:  查询所有颜色是白色的手机信息
     */

    public static void main(String[] args) throws SQLException {

        // qr
        QueryRunner qr = new QueryRunner();
        String sql1 = "select * from phone where price > ? and prodate < ?";
        Object[] params = {2000, "2019-01-01"};

        String sql2 = "select * from phone where color = ?";

        Connection connection = DruidUtils.getConnection();
        List<Object[]> objects = qr.query(connection, sql1, new ArrayListHandler(), params);

        for (Object[] object : objects) {
            // print out the result from the first query
            System.out.println(Arrays.toString(object));
        }

        List<Object[]> query = qr.query(connection, sql2, new ArrayListHandler(),"白色");
        System.out.println("-----------------Query Two----------------");
        for (Object[] result : query) {
            System.out.println(Arrays.toString(result));
        }

        DbUtils.closeQuietly(connection);
    }
}
