package com.lagou.app;

import com.lagou.utils.DruidUtils;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestTransaction {

    public static void main(String[] args) throws SQLException {

        QueryRunner qr = new QueryRunner(); // manually initiate the qr
        String sql = "select balance from account where card = ?";
        Connection connection = DruidUtils.getConnection();
        Double balance = qr.query(connection, sql, new ScalarHandler<>(), "1122334455");

        // decide to make the transaction or not
        if (balance > 5000) {
            // update the account
            String sql2 = "update account set balance = balance + ? where card = ?";
            String sql3 = "update account set balance = balance - ? where card = ?";
            Object[] param1 = {5000, "55443332211"};
            Object[] param2 = {5000, "1122334455"};
            qr.update(connection, sql2, param1);
            qr.update(connection, sql3, param2);

            // update the transaction
            String sql4 = "insert into TRANSACTION values (?,?,?,?,?)";

            // get the current local time.
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            Object[] params3 = {1, "1122334455", "转出", 5000, dtf.format(now)};
            Object[] params4 = {2, "55443332211", "转入", 5000, dtf.format(now)};
            qr.update(connection, sql4, params3);
            qr.update(connection, sql4, params4);

            System.out.println("已经成功进行转账！！");
        } else {
            System.out.println("余额不足，充币吧！！");
        }
        DbUtils.closeQuietly(connection);
    }
}
