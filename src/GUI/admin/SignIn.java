package GUI.admin;

import javax.swing.*;
import java.sql.*;

public class SignIn extends JFrame {
    static final String DB_URL = "jdbc:mysql://localhost:3306/chat_program";
    static final String USER = "root";
    static final String PASS = "";
    private JLabel signinLabel;
    private JLabel usernameLabel;
    private JTextField usernameField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton signInButton;
    private JButton signUpButton;
    private JPanel mainPanel;

    public SignIn() {
        setTitle("LOGIN - CHAT PROGRAMMING");
        add(mainPanel);
        setSize(300, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        signInButton.addActionListener(e -> {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                String username = usernameField.getText();
                //noinspection deprecation
                String password = passwordField.getText();
                Statement stm = conn.createStatement();
                String sql = "select * from user where username = '" + username + "' and password = '" + password + "'";
                ResultSet rs = stm.executeQuery(sql);
                if (rs.next()) {
                    dispose();
                    JOptionPane.showConfirmDialog(null, "Login successful");
                } else {
                    JOptionPane.showConfirmDialog(null, "Username or password wrong!!");
                    usernameField.setText("");
                    passwordField.setText("");
                }

                conn.close();
            } catch (ClassNotFoundException | SQLException ex) {
                ex.printStackTrace();
            }
        });

        signUpButton.addActionListener(e -> {
            dispose();
            SignUp res = new SignUp();
            //noinspection deprecation
            res.show();
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SignIn login = new SignIn();
        //noinspection deprecation
        login.show();
    }
}
