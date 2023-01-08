package resources;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

public class CheckExit {
    static final String DB_URL = "jdbc:mysql://localhost:3306/chat_program";
    static final String USER = "root";
    static final String PASS = "";

    public void exit(JFrame jr) {
        jr.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        int kq = JOptionPane.showConfirmDialog(null, "Do you want to exit Admin control ?", "Config", JOptionPane.YES_NO_OPTION);
        if (kq == 0) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

                String sta = "offline";
                System.out.println(sta);
            } catch (ClassNotFoundException | SQLException ex) {
                ex.printStackTrace();
            }
            System.exit(0);
        }
    }
}
