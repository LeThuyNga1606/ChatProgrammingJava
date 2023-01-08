package resources.MainFrame.Admin.adminFunctions.EditUser;

import resources.MainFrame.Admin.Admin;
import resources.MainFrame.Admin.adminFunctions.EditUser.EditUserFunctions.*;

import javax.swing.*;

public class EditUser extends JFrame {
    private JButton addNewUserButton;
    private JButton upPassButton;
    private JButton upInforButton;
    private JButton goBackButton;
    private JButton deleteUserButton;
    private JPanel mainPanel;
    private JButton blockUserButton;
    private JLabel captionLabel;

    public EditUser() {
        setTitle("Edit user - Chat programming"); // set title for registration window
        add(mainPanel); // add main panel to frame
        setSize(630, 130); // set size of window
        setLocationRelativeTo(null); // set the window display position to the center of the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // set default close
        setVisible(true);

        addNewUserButton.addActionListener(e -> {
            dispose();
            new AddNewUser();
        });

        upInforButton.addActionListener(e -> {
            dispose();
            new UpdateUserInfo();
        });

        deleteUserButton.addActionListener(e -> {
            dispose();
            new DeleteUser();
        });

        upPassButton.addActionListener(e -> {
            dispose();
            new UpdateUserPassword();
        });

        goBackButton.addActionListener(e -> {
            dispose();
            new Admin();
        });

        blockUserButton.addActionListener(e -> {
            dispose();
            new LockUser();
        });
    }

    public static void main(String[] args) {
        new EditUser();
    }
}
