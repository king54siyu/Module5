package com.lagou.app;

import com.lagou.entity.Employee;
import com.lagou.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class TestEmployee {

    /**
     * Here we test and try to use JavaBean:
     * 需求1: 查询所有的员工信息 (不包含没有部门的员工)。
     * 需求2: 查询每个员工的 姓名, 薪资 和 所属部门名称
     */

    public static void main(String[] args) throws SQLException {

        QueryRunner qr = new QueryRunner(DruidUtils.getDataSource()); // automatically

        String sql1 = "select * from employee where did is not null";
        List<Employee> employees = qr.query(sql1, new BeanListHandler<Employee>(Employee.class));

        System.out.println("The result of employees under the first condition are: ");
        for (Employee employee : employees) {
            System.out.println(employee.toString());
        }

        String sql2 = "select * from employee e left join dept d on d.id = e.did where did is not null";
        List<Object[]> objects = qr.query(sql2, new ArrayListHandler());
        System.out.println("-----------Query Two-----------");
        for (Object[] object : objects) {
            System.out.println(Arrays.toString(object));
        }
    }
}
