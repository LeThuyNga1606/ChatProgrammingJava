package resources.SignIn.partials;

import resources.Email.SendEmail;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class ForgotPassword extends JFrame {
    static final String DB_URL = "jdbc:mysql://localhost:3306/chat_program";
    static final String USER = "root";
    static final String PASS = "";
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JTextField mailTextField;
    private JLabel mailLabel;
    private JButton okButton;

    public ForgotPassword() {
        setTitle("FORGOT PASSWORD - CHAT PROGRAMMING");
        add(mainPanel);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
        okButton.addActionListener(e -> {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

                String mail = mailTextField.getText();
                Random generator = new Random();
                int randomNum = generator.nextInt(100000000) + 1;
                String resetPass = Integer.toString(randomNum);

                //noinspection InstantiationOfUtilityClass
                SendEmail mailReset = new SendEmail();
//                    mailReset.SendEmail(mail, resetPass);
                JOptionPane.showConfirmDialog(null, "Please check your mailbox!!");

                final String sql = "update user set password = '" + resetPass + "' where email = '" + mail + "'";
                Statement stm = conn.createStatement();
                stm.executeUpdate(sql);

                dispose();

                stm.close();
                conn.close();
            } catch (ClassNotFoundException | SQLException ex) {
                ex.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        new ForgotPassword();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        titleLabel = new JLabel();
        add(titleLabel);

        mailLabel = new JLabel();
        add(mailLabel);
    }
}
