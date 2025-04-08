/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package view;

import java.sql.SQLException;
import controller.DBManagement;

/**
 *
 * @author henar
 */
public class Mavenproject1 {

    public static void main(String[] args) throws SQLException {
        DBManagement.loadEmployeesData();
    }
}
