package quanlytrangsuc;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/quanlytrangsuc";
    private static final String USER = "root";
    private static final String PASSWORD = "root"; 

    public static Connection getConnection() {
        Connection conn = null;
        try {
            // Register MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Get connection
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Khong tim thay MySQL JDBC Driver.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Loi ket noi co so du lieu: " + e.getMessage());
        }
        return conn;
    }
}