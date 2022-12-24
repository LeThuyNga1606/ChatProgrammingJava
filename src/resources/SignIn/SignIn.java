package resources.SignIn;

import resources.CheckExit;
import resources.MainFrame.User;
import resources.SignIn.partials.ForgotPassword;
import resources.SignUp.SignUp;
import resources.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class SignIn extends JFrame{
    static final String DB_URL = "jdbc:mysql://localhost:3306/chat_program";
    static final String USER = "root";
    static final String PASS = "";
    private JLabel signinLabel;
    private JLabel usernameLabel;
    private JTextField usernameField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton signInButton;
    private JPanel panelMain;
    private JButton signUpButton;
    private JPanel mainPanel;
    private JCheckBox remake_password;

    public SignIn() {
        JFrame jr = new JFrame();
        jr.setTitle("LOGIN - CHAT PROGRAMMING");
        jr.add(mainPanel);
        jr.setSize(400, 300);
        jr.setLocationRelativeTo(null);
        jr.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                    String username = usernameField.getText();
                    String password = passwordField.getText();
                    Statement stm = conn.createStatement();
                    String sql = "select * from user where username = '" + username + "' and password = '" + password + "'";
                    ResultSet rs = stm.executeQuery(sql);
                    if (rs.next()) {
                        dispose();
                        JOptionPane.showConfirmDialog(null, "Login successful");
                        User user = new User();
                        user.show();
                    } else {
                        JOptionPane.showConfirmDialog(null, "Username or password wrong!!");
                        usernameField.setText("");
                        passwordField.setText("");
                    }

                    rs.close();
                    stm.close();
                    conn.close();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SignUp res = new SignUp();
                res.show();
            }
        });

        remake_password.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ForgotPassword res = new ForgotPassword();
                res.show();
            }
        });

        CheckExit exit = new CheckExit();
        exit.thoat(jr);
        jr.setVisible(true);
    }

    public static void main(String[] args){
        SignIn login = new SignIn();
        login.show();
    }
}
