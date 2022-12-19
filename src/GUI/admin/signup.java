package GUI.admin;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class signup extends JFrame {
    static final String DB_URL = "jdbc:mysql://localhost:3306/chat_program";
    static final String USER = "root";
    static final String PASS = "";
    private JPanel panelMain;
    private JTextField signUpUsername;
    private JLabel signUpUsernameLabel;
    private JLabel passwordLabel;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JLabel titleLabel;
    private JButton okButton;
    private JTextField mailField;
    private JTextField fullnameField;
    private JLabel fullnameLabel;
    private JLabel mailLabel;
    private JPanel mainPanel;
    private JLabel addr;
    private JLabel dob;
    private JLabel gender;
    private JTextField genderField;
    private JTextField dobField;
    private JTextField addrField;

    public signup() {
        setTitle("REGISTER- CHAT PROGRAMMING");
        add(panelMain);
        setSize(300, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = DriverManager.getConnection(DB_URL,USER, PASS);

                    String fullname = fullnameField.getText();
                    String email = mailField.getText();
                    String username = signUpUsername.getText();
                    String password = String.valueOf(passwordField1.getPassword());
                    String re_password = String.valueOf(passwordField2.getPassword());
                    String address = addrField.getText();
                    String DOB = dobField.getText();
                    String gen = genderField.getText();

                    if (fullname.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty() || re_password.isEmpty() || address.isEmpty() || DOB.isEmpty() || gen.isEmpty()){
                        JOptionPane.showConfirmDialog(null, "Please enter all fields");
                    }

                    if (!password.equals(re_password)){
                        JOptionPane.showConfirmDialog(null, "Confirm password does not match");
                    }

                    String check_username = "select username from user where username like '"+username+"'";
                    Statement st = conn.createStatement();
                    ResultSet rs = st.executeQuery(check_username);

                    if(rs. next()){
                        JOptionPane. showMessageDialog(null,"Already Exists");
                    }

                    else{
                        String sql = "insert into user(fullname, username, password, address, dob, gender, email)values (?,?,?,?,?,?,?)";

                        PreparedStatement stm = conn.prepareStatement(sql);
                        stm.setString(1, fullname);
                        stm.setString(2, username);
                        stm.setString(3, password);
                        stm.setString(4, address);
                        stm.setString(5, DOB);
                        stm.setString(6, gen);
                        stm.setString(7, email);

                        stm.executeUpdate();
                        dispose();
                        JOptionPane.showConfirmDialog(null, "Register successful");
                        signin login= new signin();
                        login.show();

                        conn.close();
                    }
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
        setVisible(true);
    }


    public static void main(String[] args){
        /*JFrame frame = new JFrame("Chat programming");

        frame.getContentPane().add(new signup().mainPanel);
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.pack();
        frame.setVisible(true);*/
        signup res = new signup();
        res.show();
    }
}
