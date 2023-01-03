package resources.MainFrame.Admin;

import resources.MainFrame.Admin.adminFunctions.ShowUserList;

import javax.swing.*;

public class Admin extends JFrame {
    private JPanel mainPanel;
    private JButton showUserList;
    private JButton showFriendList;
    private JButton showLoginHistory;
    private JButton editUser;
    private JButton showGroupList;

    public Admin() {
        setTitle("ADMIN - Chat programming");
        add(mainPanel);
        setSize(600, 300);
        setLocationRelativeTo(null);
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
    }

    public static void main(String[] args) {
        Admin ad = new Admin();

        //noinspection deprecation
        ad.show();
    }
}
