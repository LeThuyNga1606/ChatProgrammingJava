package resources.MainFrame.User;
import resources.SignIn.partials.ChangePassword;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class User extends JFrame{
    private JPanel mainPanel;
    private JButton ListFriend;
    private JButton ListChat;
    private JButton ListGroup;
    private JButton ListUserOnline;
    private JButton changePass;
    private JButton EditFriend;

    public User(String username){
        JFrame jr = new JFrame();
        jr.setTitle("LOGIN - CHAT PROGRAMMING");
        jr.add(mainPanel);
        jr.setSize(600, 300);
        jr.setLocationRelativeTo(null);
        jr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jr.setVisible(true);
        ListFriend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        ListChat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        ListGroup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        ListUserOnline.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ShowUserList1(username);
            }
        });
        changePass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ChangePassword(username);
            }
        });
        EditFriend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new EditFriend(username);
            }
        });
    }
}
