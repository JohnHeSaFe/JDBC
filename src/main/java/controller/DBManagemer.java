/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import model.Employee;

/**
 *
 * @author henar
 */
public class DBManagemer {
    
    
    private static String dataConection = "jdbc:mysql://localhost:3306/";
    private static String dataBase = "employeeJavaDAM";
    private static String user = "root";
    private static String password = "1234";
    public static Connection con;
    
    public DBManagemer() {
        try {
            con = DriverManager.getConnection(dataConection, user, password);
            try {
                createDB();
                createEmployeesTable();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void loadEmployeesData() throws SQLException {
        String query = "select * from serie";
        Statement stmt = null;
        ResultSet rs = null;
        Employee employee;

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                employee = new Employee(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getInt("age"),
                    rs.getString("department"),
                    rs.getDouble("salary")
                );
                
                EmployeeManager.addEmployeeToList(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
    }
        
    private void createDB() throws SQLException {
        String query = "create database if not exists " + dataBase + ";";
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            stmt.executeUpdate(query);
            con = DriverManager.getConnection(
                dataConection + dataBase,
                user,
                password
            );
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            stmt.close();
        }
    }

    private void createEmployeesTable() throws SQLException {
        String query = "create table if not exists Employees("
                + "id int primary key auto_increment, "
                + "name varchar(100) not null, "
                + "age int not null, "
                + "department varchar(100) not null, "
                + "salary decimal(10, 2) not null"
                + ");";
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            stmt.close();
        }
    }
    
    public int insertEmployee(Employee employee) throws SQLException {
        String query = "insert into Employees(name, age, department, salary) "
                + "values('" + employee.getName() + "', " + employee.getAge() + ", '" + employee.getDepartment() + "', " + employee.getSalary() + ")";
        Statement stmt = null;
        ResultSet rs = null;
        int id = -1;
        
        try {
            stmt = con.createStatement();
            stmt.executeUpdate(query);
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            stmt.close();
        }
        
        return id;
    }
    
    public void updateEmployee(Employee employee) throws SQLException {
        String query = "update Employees "
                + "set name = '" + employee.getName() + "', age = " + employee.getAge() + ", department = '" + employee.getDepartment() + "', salary = " + employee.getSalary() + " "
                + "where id = " + employee.getId();
        Statement stmt = null;
        
        try {
            stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            stmt.close();
        }
    }
    
    public void deleteEmployee(Employee employee) throws SQLException {
        String query = "delete from Employees "
                + "where id = " + employee.getId();
        Statement stmt = null;
        
        try {
            stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            stmt.close();
        }
    }
  
}
