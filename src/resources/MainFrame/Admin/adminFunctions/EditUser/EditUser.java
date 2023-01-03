package resources.MainFrame.Admin.adminFunctions.EditUser;

import resources.MainFrame.Admin.Admin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditUser extends JFrame{
    private JButton addNewUserButton;
    private JButton upPassButton;
    private JButton upInforButton;
    private JButton goBackButton;
    private JButton deleteUserButton;
    private JPanel mainPanel;
    private JButton blockUserButton;

    public EditUser(){
        setTitle("Edit user - Chat programming"); //set title for registration window
        add(mainPanel); //add main panel to frame
        setSize(600, 300); //set size of window
        setLocationRelativeTo(null); //set the location of window relative to the current component c (in this case the component c is 'null' so that we're setting the window is centered on the screen)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //set default close
        setVisible(true);

        addNewUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new AddNewUser();
            }
        });
        upInforButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new UpdateInforUser();
            }
        });
        deleteUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new DeleteUser();
            }
        });
        upPassButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new UpdatePassUser();
            }
        });
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Admin();
            }
        });
        blockUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new blockUser();
            }
        });
    }
    public static void main(String[] args) {
        new EditUser();
    }
}
