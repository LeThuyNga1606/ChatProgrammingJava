package GUI.admin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class signin extends JFrame{
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

    public signin() {
        setTitle("LOGIN - CHAT PROGRAMMING");
        add(mainPanel);
        setSize(300, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = DriverManager.getConnection(DB_URL,USER, PASS);
                    String username = usernameField.getText();
                    String password = passwordField.getText();
                    Statement stm = conn.createStatement();
                    String sql = "select * from user where username = '"+username+"' and password = '"+password+"'";
                    ResultSet rs = stm.executeQuery(sql);
                    if (rs.next()) {
                        dispose();
                        JOptionPane.showConfirmDialog(null, "Login successful");
                    }
                    else{
                        JOptionPane.showConfirmDialog(null, "Username or password wrong!!");
                        usernameField.setText("");
                        passwordField.setText("");
                    }

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

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                signup res= new signup();
                res.show();
            }
        });

        setVisible(true);
    }

    public static void main(String[] args){
        /*JFrame frame = new JFrame("Chat programming");
        frame.getContentPane().add(new signin().mainPanel);
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.pack();
        frame.setVisible(true);*/
        signin login = new signin();
        login.show();
    }
}
