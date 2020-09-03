package com.lagou.entity;


import java.io.Serializable;
import java.util.Date;

/**
 * id INT PRIMARY KEY AUTO_INCREMENT ,
 * 	NAME VARCHAR (30),		-- 员工姓名
 * 	age DOUBLE ,				-- 员工年龄
 * 	sex VARCHAR (6),			-- 员工性别
 * 	salary DOUBLE ,			-- 薪水
 * 	empdate DATE ,			-- 入职日期
 * 	did INT,
 */

public class Employee implements Serializable {

    private int id;
    private String Name;
    private double age;
    private String sex;
    private double salary;
    private Date empdate;
    private int did;

    public Employee() {
    }

    public Employee(int id, String name, double age, String sex, double salary, Date empdate, int did) {
        this.id = id;
        Name = name;
        this.age = age;
        this.sex = sex;
        this.salary = salary;
        this.empdate = empdate;
        this.did = did;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Date getEmpdate() {
        return empdate;
    }

    public void setEmpdate(Date empdate) {
        this.empdate = empdate;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", salary=" + salary +
                ", empdate=" + empdate +
                ", did=" + did +
                '}';
    }
}
