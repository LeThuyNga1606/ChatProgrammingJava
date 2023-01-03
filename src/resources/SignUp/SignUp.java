package resources.SignUp;

import resources.SignIn.SignIn;

import javax.swing.*;
import java.sql.*;
import java.util.Random;

public class SignUp extends JFrame {
    static final String DB_URL = "jdbc:mysql://localhost:3306/chat_program";
    static final String USER = "root";
    static final String PASS = "kendark";

    private JPanel mainPanel;
    private JLabel titleLabel;
    private JLabel fullnameLabel;
    private JTextField fullnameField;
    private JLabel usernameLabel;
    private JTextField signUpUsername;
    private JLabel passwordLabel1;
    private JLabel passwordLabel2;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JButton okBtn;
    private JLabel mailLabel;
    private JTextField mailField;
    private JTextField genderField;
    private JLabel dobLabel;
    private JTextField dobField;
    private JLabel addressLabel;
    private JTextField addressField;
    private JLabel genderLabel;
    private JButton goBackBtn;

    public SignUp() {
        setTitle("REGISTER - CHAT PROGRAMMING"); //set title for registration window
        add(mainPanel); //add main panel to frame
        setSize(300, 300); //set size of window
        setLocationRelativeTo(null); //set the location of window relative to the current component c (in this case the component c is 'null' so that we're setting the window is centered on the screen)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //set default close
        setVisible(true);

        okBtn.addActionListener(e -> {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver"); //declare the jdbc diver
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); //declare the connection of program's database

                // Read the information from the form that are filled by user
                Random generator = new Random(); //generate random number
                int temp = generator.nextInt(100); //generate random number from 0 - 99
                String id = Integer.toString(temp); //assign the 'temp' to the 'id'
                String fullname = fullnameField.getText();
                String email = mailField.getText();
                String username = signUpUsername.getText();
                String password = String.valueOf(passwordField1.getPassword());
                String re_password = String.valueOf(passwordField2.getPassword());
                String address = addressField.getText();
                String DOB = dobField.getText();
                String gen = genderField.getText();

                // Check if any information is empty
                if (fullname.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty() || re_password.isEmpty() || address.isEmpty() || DOB.isEmpty() || gen.isEmpty()) {
                    JOptionPane.showConfirmDialog(null, "Please enter all fields");
                }

                // Check if the password and re-enter password are the same
                if (!password.equals(re_password)) {
                    JOptionPane.showConfirmDialog(null, "Confirm password does not match");
                }

                // Checking if the username is in the database
                String check_username = "select username from user where username like '" + username + "'";
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(check_username);

                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Already Exists");
                } else {
                    // Set up the SQL statement to execute
                    String sql = "insert into user(id, fullname, username, password, address, dob, gender, mail)values (?,?,?,?,?,?,?,?)";

                    PreparedStatement stm = conn.prepareStatement(sql);
                    stm.setString(1, id);
                    stm.setString(2, fullname);
                    stm.setString(3, username);
                    stm.setString(4, password);
                    stm.setString(5, address);
                    stm.setString(6, DOB);
                    stm.setString(7, gen);
                    stm.setString(8, email);

                    stm.executeUpdate(); //execute the statement
                    dispose();
                    JOptionPane.showConfirmDialog(null, "Register successful");
                    SignIn login = new SignIn();

                    //noinspection deprecation
                    login.show();

                    conn.close();
                }
            } catch (ClassNotFoundException | SQLException ex) {
                ex.printStackTrace();
            }
        });
        goBackBtn.addActionListener(e -> {
            dispose();
            new SignIn();
        });
    }

    public static void main(String[] args) {
        new SignUp();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        titleLabel = new JLabel("SIGN UP");
        add(titleLabel);

        fullnameLabel = new JLabel("FULL NAME");
        add(fullnameLabel);

        mailLabel = new JLabel("MAIL");
        add(mailLabel);

        usernameLabel = new JLabel("USERNAME");
        add(usernameLabel);

        passwordLabel1 = new JLabel("PASSWORD");
        add(passwordLabel1);

        passwordLabel2 = new JLabel("RE-ENTER PASSWORD");
        add(passwordLabel2);

        addressLabel = new JLabel("ADDRESS");
        add(addressLabel);

        dobLabel = new JLabel("DOB");
        add(dobLabel);

        genderLabel = new JLabel("GENDER");
        add(genderLabel);

        goBackBtn = new JButton("GO BACK");
        add(goBackBtn);

        okBtn = new JButton("OK");
        add(okBtn);
    }
}