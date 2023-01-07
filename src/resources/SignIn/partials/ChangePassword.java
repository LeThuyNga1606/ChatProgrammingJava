package resources.SignIn.partials;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ChangePassword extends JFrame{
    static final String DB_URL = "jdbc:mysql://localhost:3306/chat_program";
    static final String USER = "root";
    static final String PASS = "";
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JButton okButton;
    private JPasswordField newPasswordField;
    private JLabel newPasswordLabel;
    private JPasswordField reEnterNewPasswordField;
    private JLabel reEnterNewPasswordLabel;

    public ChangePassword(String username){
        setTitle("CHANGE PASSWORD - CHAT PROGRAMMING");
        add(mainPanel);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newPass = String.valueOf(newPasswordField.getPassword());
                String re_newPass = String.valueOf(reEnterNewPasswordField.getPassword());
                String name = username;

                if (newPass.isEmpty() || re_newPass.isEmpty())
                    JOptionPane.showMessageDialog(null, "Please enter all fields");

                if(!newPass.equals(re_newPass))
                    JOptionPane.showConfirmDialog(null, "Confirm password does not match");

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = DriverManager.getConnection(DB_URL,USER, PASS);

                    final String sql = "update user set password = '"+newPass+"' where username = '"+name+"'";
                    Statement stm = conn.createStatement();
                    stm.executeUpdate(sql);

                    dispose();

                    stm.close();
                    conn.close();
                }catch (ClassNotFoundException ex)
                {
                    ex.printStackTrace();
                }
                catch (SQLException ex)
                {
                    ex.printStackTrace();
                }
            }
        });
    }
}
