package resources.MainFrame.Admin.adminFunctions.EditUser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class UpdateInforUser extends JFrame {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/chat_program";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "";
    private JButton goBackButton;
    private JButton updateButton;
    private JTextField fullnameField;
    private JTextField emailField;
    private JTextField usernameField;
    private JTextField addrField;
    private JTextField dobField;
    private JTextField genderField;
    private JPanel mainPanel;
    private JTextField IDUserField;

    public UpdateInforUser(){
        setTitle("Update information user - Chat programming"); //set title for registration window
        add(mainPanel); //add main panel to frame
        setSize(400, 600); //set size of window
        setLocationRelativeTo(null); //set the location of window relative to the current component c (in this case the component c is 'null' so that we're setting the window is centered on the screen)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //set default close
        setVisible(true);
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver"); //declare the jdbc diver
                    Connection conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD); //declare the connection of program's database

                    // Read the information from the form that are filled by user
                    String id = IDUserField.getText();
                    String fullname = fullnameField.getText();
                    String email = emailField.getText();
                    String username = usernameField.getText();
                    String address = addrField.getText();
                    String DOB = dobField.getText();
                    String gen = genderField.getText();

                    // Check if any information is empty
                    if (fullname.isEmpty() || email.isEmpty() || username.isEmpty() || address.isEmpty() || DOB.isEmpty() || gen.isEmpty()) {
                        JOptionPane.showConfirmDialog(null, "Please enter all fields");
                    }

                    // Checking if the username is in the database
                    String check_username = "select id from user where id like '" + id + "'";
                    Statement st = conn.createStatement();
                    ResultSet rs = st.executeQuery(check_username);

                    if (rs.next()) {
                        String sql = "update user set fullname = ?, username = ?, address = ?, dob = ?, gender = ?, email = ? where id = ?";

                        PreparedStatement stm = conn.prepareStatement(sql);
                        stm.setString(1, fullname);
                        stm.setString(2, username);
                        stm.setString(3, address);
                        stm.setString(4, DOB);
                        stm.setString(5, gen);
                        stm.setString(6, email);
                        stm.setString(7, id);
                        stm.executeUpdate(); //execute the statement
                        dispose();
                        JOptionPane.showConfirmDialog(null, "Update successful");
                        dispose();
                        new EditUser();

                        stm.close();
                        conn.close();
                    } else {
                        JOptionPane.showMessageDialog(null, "User does not exist");
                    }
                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new EditUser();
            }
        });
    }
    public static void main(String[] args) {
        new UpdateInforUser();
    }
}
