/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package view;

import java.sql.SQLException;
import controller.DBManager;

/**
 *
 * @author henar
 */
public class Main {

    public static void main(String[] args) throws SQLException {
        DBManager db = new DBManager();
        db.loadEmployeesData();
        
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
    }
}
