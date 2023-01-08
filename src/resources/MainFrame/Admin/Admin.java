package resources.MainFrame.Admin;

import resources.MainFrame.Admin.adminFunctions.EditUser.EditUser;
import resources.MainFrame.Admin.adminFunctions.ShowUserList;

import javax.swing.*;

public class Admin extends JFrame {
    private JPanel mainPanel;
    private JButton showUserList;
    private JButton showFriendList;
    private JButton showLoginHistory;
    private JButton editUser;
    private JButton showGroupList;
    private JButton exitButton;

    public Admin() {
        add(mainPanel);
        setTitle("ADMIN - Chat programming");
        setSize(630, 130);
        setLocationRelativeTo(null); // set the window display position to the center of the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        showUserList.addActionListener(e -> {
            dispose();
            new ShowUserList();
        });

        editUser.addActionListener(e -> {
            dispose();
            new EditUser();
        });

        showLoginHistory.addActionListener(e -> {

        });

        showFriendList.addActionListener(e -> {

        });

        showGroupList.addActionListener(e -> {

        });

        exitButton.addActionListener(e -> {
            dispose();
        });
    }

    public static void main(String[] args) {
        new Admin();
    }
}
