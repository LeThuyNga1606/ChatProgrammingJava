package resources;

import javax.swing.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

public class CheckExit {
    static final String DB_URL = "jdbc:mysql://localhost:3306/chat_program";
    static final String USER = "root";
    static final String PASS = "";

    public void thoat(JFrame jr)
    {
        jr.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE );
        int kq = JOptionPane.showConfirmDialog(null, "Do you want to exit Admin control ?","Congfig",JOptionPane.YES_NO_OPTION);
        if(kq == 0)
        {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(DB_URL,USER, PASS);

                String sta = "offline";
                System.out.println(sta);

//                final String sql = "update userOnline set status = '"+sta+"' where id = '"+0001+"'";
//                Statement stm = conn.createStatement();
//                stm.executeUpdate(sql);
//
//                stm.close();
//                conn.close();
            }catch (ClassNotFoundException ex)
            {
                ex.printStackTrace();
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
            System.exit(0);
        }
    }
}
