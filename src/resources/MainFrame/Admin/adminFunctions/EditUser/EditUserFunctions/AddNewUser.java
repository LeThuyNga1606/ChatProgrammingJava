package resources.MainFrame.Admin.adminFunctions.EditUser.EditUserFunctions;

import resources.MainFrame.Admin.adminFunctions.EditUser.EditUser;

import javax.swing.*;
import java.sql.*;
import java.util.Random;

public class AddNewUser extends JFrame {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/chat_program";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "";
    private JTextField fullnameField;
    private JTextField emailField;
    private JTextField usernameField;
    private JPasswordField passField;
    private JTextField addrField;
    private JTextField dobField;
    private JTextField genderField;
    private JButton addButton;
    private JButton goBackButton;
    private JPanel mainPanel;
    private JLabel captionLabel;
    private JLabel fullnameLabel;
    private JLabel emailLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel addressLabel;
    private JLabel dobLabel;
    private JLabel genderLabel;

    public AddNewUser() {
        setTitle("Add new user - Chat programming"); //set title for registration window
        add(mainPanel); //add main panel to frame
        setSize(400, 600); //set size of window
        setLocationRelativeTo(null); //set the window display position to the center of the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //set default close
        setVisible(true);

        goBackButton.addActionListener(e -> {
            dispose();
            new EditUser();
        });

        addButton.addActionListener(e -> {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver"); //declare the jdbc diver
                Connection conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD); //declare the connection of program's database

                // Read the information from the form that are filled by user
                Random generator = new Random(); //generate random number
                int temp = generator.nextInt(100); //generate random number from 0 - 99
                String id = Integer.toString(temp); //assign the 'temp' to the 'id'
                String fullname = fullnameField.getText();
                String email = emailField.getText();
                String username = usernameField.getText();
                String password = String.valueOf(passField.getPassword());
                String address = addrField.getText();
                String DOB = dobField.getText();
                String gen = genderField.getText();

                // Check if any information is empty
                if (fullname.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty() || address.isEmpty() || DOB.isEmpty() || gen.isEmpty()) {
                    JOptionPane.showConfirmDialog(null, "Please enter all fields");
                }

                // Checking if the username is in the database
                String check_username = "select username from user where username like '" + username + "'";
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(check_username);

                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Already Exists");
                } else {
                    // Set up the SQL statement to execute
                    String sql = "insert into user(id, fullname, username, password, address, dob, gender, email, stateAcc)values (?,?,?,?,?,?,?,?,?)";

                    PreparedStatement stm = conn.prepareStatement(sql);
                    stm.setString(1, id);
                    stm.setString(2, fullname);
                    stm.setString(3, username);
                    stm.setString(4, password);
                    stm.setString(5, address);
                    stm.setString(6, DOB);
                    stm.setString(7, gen);
                    stm.setString(8, email);
                    stm.setString(9, "activated");

                    stm.executeUpdate(); //execute the statement
                    dispose();
                    JOptionPane.showConfirmDialog(null, "Add successful");
                    dispose();
                    new EditUser();

                    stm.close();
                    conn.close();
                }
            } catch (ClassNotFoundException | SQLException ex) {
                ex.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        new AddNewUser();
    }
}
