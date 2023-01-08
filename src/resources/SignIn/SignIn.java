package resources.SignIn;

import resources.Client;
import resources.SignIn.partials.ForgotPassword;
import resources.SignUp.SignUp;
import javax.swing.*;
import java.sql.*;
import java.util.Properties;


public class SignIn extends JFrame {
    static final String DB_URL = "jdbc:mysql://localhost:3306/chat_program";
    static final String USER = "root";
    static final String PASS = "kendark";
    public String username;
    private JLabel signinLabel;
    private JLabel usernameLabel;
    private JTextField usernameField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton signInBtn;
    private JButton signUpBtn;
    private JPanel mainPanel;
    private JButton forgotPassBtn;
    private JCheckBox remake_password;
    private String password;


    public SignIn() {
        setTitle("LOGIN - CHAT PROGRAMMING"); //set title for registration window
        add(mainPanel); //add main panel to frame
        setSize(400, 300); //set size of window
        setLocationRelativeTo(null); //set the location of window relative to the current component c (in this case the component c is 'null' so that we're setting the window is centered on the screen)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //set default close
        setVisible(true);


        signInBtn.addActionListener(e -> {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                username = usernameField.getText();
                password = String.valueOf(passwordField.getPassword());
                Statement stm = conn.createStatement();
                String sql = "select * from user where username = '" + username + "' and password = '" + password + "'";
                ResultSet rs = stm.executeQuery(sql);
                if (rs.next()) {
                    dispose();
                    JOptionPane.showConfirmDialog(null, "Login successful");

                    rs.close();
                    stm.close();
                    conn.close();

                    dispose();
                    new Client(username);
                } else {
                    JOptionPane.showConfirmDialog(null, "Username or password wrong!!");
                    usernameField.setText("");
                    passwordField.setText("");
                }

                rs.close();
                stm.close();
                conn.close();
            } catch (ClassNotFoundException | SQLException ex) {
                ex.printStackTrace();
            }
        });

        signUpBtn.addActionListener(e -> {
            dispose();
            new SignUp();
        });

        forgotPassBtn.addActionListener(e -> {
            dispose();
            new ForgotPassword();
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

        /*remake_password.addItemListener(new ItemListener() {
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
                        int pass = (int) (Math.random() * (999999 - 1000));
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
        });*/
    }

    public static void main(String[] args) {
        new SignIn();
    }


    void sendMail(String resetPass, String emailToSend) {
        final String fromEmail = "laptrinhjava20clc@gmail.com";
        final String passwordEmail = "chatprogram@java";
        String host = "smtp.gmail.com";

        //noinspection MismatchedQueryAndUpdateOfCollection
        Properties prop = new Properties();
        prop.put("mail.smtp.host", host);
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        signinLabel = new JLabel("LOG IN");
        add(signinLabel);

        usernameLabel = new JLabel("USERNAME");
        add(usernameLabel);

        passwordLabel = new JLabel("PASSWORD");
        add(passwordLabel);
    }
}
