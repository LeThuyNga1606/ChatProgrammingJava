package resources.MainFrame.Admin.adminFunctions.EditUser.EditUserFunctions;

import resources.MainFrame.Admin.adminFunctions.EditUser.EditUser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class UpdateUserInfo extends JFrame {
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
    private JLabel captionLabel;
    private JLabel fullnameLabel;
    private JLabel emailLabel;
    private JLabel usernameLabel;
    private JLabel addressLabel;
    private JLabel dobLabel;
    private JLabel genderLabel;
    private JTextField idUserField;
    private JLabel idUserLabel;

    public UpdateUserInfo() {
        setTitle("Update information user - Chat programming"); //set title for registration window
        add(mainPanel); //add main panel to frame
        setSize(400, 600); //set size of window
        setLocationRelativeTo(null); //set the window display position to the center of the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //set default close
        setVisible(true);
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver"); //declare the jdbc diver
                    Connection conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD); //declare the connection of program's database

                    // Read the information from the form that are filled by user
                    String id = idUserField.getText();
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
        new UpdateUserInfo();
    }
}
