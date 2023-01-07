package resources.MainFrame.Admin;

import resources.MainFrame.Admin.adminFunctions.EditUser.EditUser;
import resources.MainFrame.Admin.adminFunctions.ShowUserList;
import resources.MainFrame.Admin.adminFunctions.ShowUserLogInList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Admin extends JFrame{
    private JPanel mainPanel;
    private JButton showListUser;
    private JButton showListFriend;
    private JButton showHistoryLogin;
    private JButton editUser;
    private JButton showListGroup;

    public Admin(){
        setTitle("ADMIN - Chat programming");

        add(mainPanel);
        setSize(600, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        showListUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ShowUserList();
            }
        });
        editUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new EditUser();
            }
        });
        showHistoryLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ShowUserLogInList();
            }
        });
        showListFriend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        showListGroup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args){
        Admin ad = new Admin();
        ad.show();
    }
}
