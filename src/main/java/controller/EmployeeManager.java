/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import static controller.DBManagement.con;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import model.Employee;

/**
 *
 * @author henar
 */
public class EmployeeManager {
    public static HashMap<Integer, Employee> employees = new HashMap<>();
    
    private static final DBManagement db = new DBManagement();
    
    public static void insertEmployee(String name, int age, String department, double salary) throws SQLException {
        Employee employee = new Employee(name, age, department, salary);
        
        int id = db.insertEmployee(employee);
        
        employee.setId(id);
        employees.put(employee.getId(), employee);
    }
    
    public static boolean employeeExists(int id) throws SQLException {
        Iterator it = employees.keySet().iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            if (employees.get(key).getId() == id) {
                return true;
            }
        }
        return false;
    }
    
    public static void updateEmployee(int id, String name, int age, String department, double salary) throws SQLException {
        Iterator it = employees.keySet().iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            if (employees.get(key).getId() == id) {
                employees.get(key).setName(name);
                employees.get(key).setAge(age);
                employees.get(key).setDepartment(department);
                employees.get(key).setSalary(salary);
                return;
            }
        }
    }
}
