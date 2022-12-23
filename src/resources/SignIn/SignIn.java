package resources.SignIn;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;
import java.util.Properties;

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
    private JPanel panelMain;
    private JButton signUpButton;
    private JPanel mainPanel;
    private JCheckBox remake_password;

    public SignIn() {
        setTitle("LOGIN - CHAT PROGRAMMING");
        add(mainPanel);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


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
//                SignUp res= new SignUp();
//                res.show();
            }
        });

        /*remake_password.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = DriverManager.getConnection(DB_URL,USER, PASS);
                    String username = usernameField.getText();
                    Statement stm = conn.createStatement();

                    final String sql = "select * from user where username = '"+username+"'";
                    ResultSet rs = stm.executeQuery(sql);

                    while (rs.next()) {
                        String emailToSend = rs.getString("email");
                        int pass = (int)Math.random() * ( 999999 - 1000 );
                        String resetPass = Integer.toString(pass);

                        sendMail(resetPass, emailToSend);

                        JOptionPane.showConfirmDialog(null, "Please check your mailbox!!");
                    }

                    rs.close();
                    stm.close();
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
        });*/

        remake_password.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                    String username = usernameField.getText();
                    Statement stm = conn.createStatement();

                    final String sql = "select * from user where username = '" + username + "'";
                    ResultSet rs = stm.executeQuery(sql);

                    if (rs.next()) {
                        String emailToSend = rs.getString("email");
                        int pass = (int) Math.random() * (999999 - 1000);
                        String resetPass = Integer.toString(pass);

                        if (e.getStateChange() == 1) {
                            sendMail(resetPass, emailToSend);
                        }

                        JOptionPane.showConfirmDialog(null, "Please check your mailbox!!");
                    }
                    rs.close();
                    stm.close();
                    conn.close();
                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SignIn login = new SignIn();

        //noinspection deprecation
        login.show();
    }

    void sendMail(String resetPass, String emailToSend) {
        final String fromEmail = "laptrinhjava20clc@gmail.com";
        final String passwordEmail = "chatprogram@java";
        String host = "smtp.gmail.com";
        Properties prop = new Properties();
        prop.put("mail.smtp.host", host);
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

//        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(fromEmail, passwordEmail);
//            }
//        });
//        try {
//            MimeMessage message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(fromEmail));
//            message.setSubject("Reset password");
//            message.setText("Your new password is: "+ resetPass);
//            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailToSend));

//            Transport.send(message);;
//        } catch (MessagingException ex2) {
//            ex2.printStackTrace();
//        }
    }
}
