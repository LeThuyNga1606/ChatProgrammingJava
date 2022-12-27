package resources.MainFrame.Admin;

import resources.MainFrame.Admin.adminFunctions.ShowUserList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        showUserList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new ShowUserList();
            }
        });
        editUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        showLoginHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        showFriendList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        showGroupList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        Admin ad = new Admin();
        ad.show();
    }
}
