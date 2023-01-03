package resources.MainFrame.Admin.adminFunctions.EditUser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class UpdatePassUser extends JFrame{
    private static final String DB_URL = "jdbc:mysql://localhost:3306/chat_program";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "";
    private JPanel mainPanel;
    private JButton goBackButton;
    private JButton updateButton;
    private JTextField idField;
    private JTextField usernameField;
    private JPasswordField passField;

    public UpdatePassUser() {
        setTitle("Update password user - Chat programming"); //set title for registration window
        add(mainPanel); //add main panel to frame
        setSize(400, 600); //set size of window
        setLocationRelativeTo(null); //set the location of window relative to the current component c (in this case the component c is 'null' so that we're setting the window is centered on the screen)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //set default close
        setVisible(true);
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new EditUser();
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver"); //declare the jdbc diver
                    Connection conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD); //declare the connection of program's database

                    // Read the information from the form that are filled by user
                    String id = idField.getText();
                    String username = usernameField.getText();
                    String newPass = String.valueOf(passField.getPassword());

                    // Check if any information is empty
                    if (username.isEmpty() || id.isEmpty()) {
                        JOptionPane.showConfirmDialog(null, "Please enter all fields");
                    }

                    // Checking if the username is in the database
                    String check_user = "select id, username from user where id ='" + id + "' and username='"+username+"'";
                    Statement st = conn.createStatement();
                    ResultSet rs = st.executeQuery(check_user);

                    if (rs.next()) {
                        // Set up the SQL statement to execute
                        String sql = "update user set password = '"+newPass+"' where id = '"+id+"' and username= '"+username+"'";
                        Statement stm = conn.createStatement();
                        stm.executeUpdate(sql);
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
    }
    public static void main(String[] args) {
        new UpdatePassUser();
    }
}
