package resources.MainFrame;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class User extends JFrame{
    private JPanel mainPanel;
    private JButton ListFriend;
    private JButton ListChat;
    private JButton ListGroup;
    private JButton ListUserOnline;
    public User(){
        JFrame jr = new JFrame();
        jr.setTitle("USER - CHAT PROGRAMMING");
        jr.add(mainPanel);
        jr.setSize(400, 300);
        jr.setLocationRelativeTo(null);
        jr.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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

            }
        });
    }
    public static void main(String[] args){
        User res = new User();
        res.show();
    }
}