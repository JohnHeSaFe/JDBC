/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import model.Employee;

/**
 *
 * @author henar
 */
public class EmployeeManager {
    private static final HashMap<Integer, Employee> employees = new HashMap<>();
    
    private static final DBManager db = new DBManager();
    
    public static Employee searchEmployee(int id) throws SQLException {
        Iterator<Integer> it = employees.keySet().iterator();
        while (it.hasNext()) {
            Integer key = it.next();
            if (employees.get(key).getId() == id) {
                return employees.get(key);
            }
        }
        return null;
    }
    
    public static boolean employeeExists(int id) throws SQLException {
        return searchEmployee(id) != null;
    }

    public static void insertEmployee(String name, int age, String department, double salary) throws SQLException {
        Employee employee = new Employee(name, age, department, salary);
        
        int id = db.insertEmployee(employee);
        employee.setId(id);
        
        employees.put(employee.getId(), employee);
    }
    
    public static void updateEmployee(int id, String name, int age, String department, double salary) throws SQLException {
        Employee employee = searchEmployee(id);
        
        employee.setName(name);
        employee.setAge(age);
        employee.setDepartment(department);
        employee.setSalary(salary);
        
        db.updateEmployee(employee);
    }
    
    public static void deleteEmployee(int id) throws SQLException {
        Employee employee = searchEmployee(id);
        
        db.deleteEmployee(employee);
                
        employees.remove(employee.getId());
    }
    
    public static HashMap<Integer, Employee> getListEmployees() {
        return employees;
    }
    
    public static void addEmployeeToList(Employee employee) {
        employees.put(employee.getId(), employee);
    }
}
