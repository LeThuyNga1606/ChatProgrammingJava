package resources.MainFrame.User;

import resources.SignIn.partials.ChangePassword;

import javax.swing.*;

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

        ListFriend.addActionListener(e -> {

        });

        ListChat.addActionListener(e -> {

        });

        ListGroup.addActionListener(e -> {

        });

        ListUserOnline.addActionListener(e -> {
            dispose();
            new ShowUserList1(username);
        });

        changePass.addActionListener(e -> {
            dispose();
            new ChangePassword(username);
        });

        EditFriend.addActionListener(e -> {
            dispose();
            new EditFriend(username);
        });
    }
}
