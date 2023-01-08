package resources.MainFrame.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class EditFriend extends JFrame {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/chat_program";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "";
    private JPanel mainPanel;
    private JButton deleteFriendButton;
    private JButton goBackButton;
    private JButton addFriendButton;
    private JTextField nameField;

    public EditFriend(String username) {
        setTitle("Edit friend list - Chat programming"); //set title for registration window
        add(mainPanel); //add main panel to frame

        setSize(400, 600); //set size of window
        setLocationRelativeTo(null); //set the location of window relative to the current component c (in this case the component c is 'null' so that we're setting the window is centered on the screen)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //set default close
        setVisible(true);
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new User(username);
            }
        });
        addFriendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver"); //declare the jdbc diver
                    Connection conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD); //declare the connection of program's database

                    // Read the information from the form that are filled by user
                    String name_friend = nameField.getText();
                    String name = username;

                    // Check if any information is empty
                    if (name_friend.isEmpty()) {
                        JOptionPane.showConfirmDialog(null, "Please enter field");
                    }

                    // Checking if the username is in the database
                    String check_username = "select * from user where username like '" + name + "'";
                    Statement st = conn.createStatement();
                    ResultSet rs = st.executeQuery(check_username);

                    if (rs.next()) {
                        String id = rs.getString("id");
                        // Set up the SQL statement to execute
                        String sql = "insert into list_friend(iduser, username, username_friend)values (?,?,?)";

                        PreparedStatement stm = conn.prepareStatement(sql);
                        stm.setString(1, id);
                        stm.setString(2, name);
                        stm.setString(3, name_friend);

                        stm.executeUpdate(); //execute the statement
                        dispose();
                        JOptionPane.showConfirmDialog(null, "Add successful");

                        stm.close();
                        conn.close();
                    }
                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        deleteFriendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver"); //declare the jdbc diver
                    Connection conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD); //declare the connection of program's database

                    // Read the information from the form that are filled by user
                    String name_friend = nameField.getText();
                    String name = username;

                    // Check if any information is empty
                    if (name_friend.isEmpty()) {
                        JOptionPane.showConfirmDialog(null, "Please enter field");
                    }
                        // Set up the SQL statement to execute
                    String sql = "delete from list_friend where username = '"+name+"' and username_friend = '"+name_friend+"'";

                    PreparedStatement stm = conn.prepareStatement(sql);
                    stm.executeUpdate();

                    JOptionPane.showConfirmDialog(null, "Delete successful");

                    stm.close();
                    conn.close();
                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
