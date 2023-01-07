package resources.MainFrame.Admin.adminFunctions;

import resources.MainFrame.Admin.Admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class ShowUserLogInList extends JFrame {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/chat_program";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "";
    private JPanel mainPanel;
    private JButton refresh;
    private JLabel mainTitle;
    private JTable displayFrame;
    private JScrollPane scroll;
    private JButton goBack;

    public ShowUserLogInList() {
        setTitle("User log in list - Chat programming"); //set title for registration window
        add(mainPanel); //add main panel to frame

        setSize(400, 600); //set size of window
        setLocationRelativeTo(null); //set the location of window relative to the current component c (in this case the component c is 'null' so that we're setting the window is centered on the screen)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //set default close
        setVisible(true);
        goBack.addActionListener(e -> {
            dispose();
            new Admin();
        });
        refresh.addActionListener(e -> {
            dispose();
            new ShowUserLogInList();
        });
    }

    public static void main(String[] args) {
        new ShowUserLogInList();
    }

    public void readDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // connect to database
            Connection conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);

            // crate statement
            Statement stmt = conn.createStatement();

            // get data from table 'status'
            ResultSet rs = stmt.executeQuery("select * from user_online order by username, time"); // get status' data

            // create a table model with the appropriate column headers and with 0 rows (to start with)
            String[] columnNames = {"User ID", "Username", "Status", "Time"};
            DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

            while (rs.next()) {
                String idUser = rs.getString("iduser");
                String name = rs.getString("username");
                String status = rs.getString("is_online");
                String time = rs.getString("time");

                if (status.equals("1")) {
                    status = "Online";
                } else {
                    status = "Offline";
                }

                // create a single array of one row's worth of data
                String[] data = {idUser, name, status, time};

                // and add this row of data into the table model
                tableModel.addRow(data);
            }

            this.displayFrame = new JTable(tableModel);
            this.scroll = new JScrollPane(this.displayFrame);

            stmt.close();   // close the statement
            conn.close();   // close the connection
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        mainTitle = new JLabel("User list");
        add(mainTitle);

        readDatabase();
        add(scroll);
    }
}
