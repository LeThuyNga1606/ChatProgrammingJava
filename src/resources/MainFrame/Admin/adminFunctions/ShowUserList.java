package resources.MainFrame.Admin.adminFunctions;

import resources.MainFrame.Admin.Admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class ShowUserList extends JFrame {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/chat_program";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "kendark";
    private JPanel mainPanel;
    private JButton refresh;
    private JLabel mainTitle;
    private JTable displayFrame;
    private JScrollPane scroll;
    private JButton goBack;

    public ShowUserList() {
        setTitle("User list - Chat programming"); //set title for registration window
        add(mainPanel); //add main panel to frame

        setSize(400, 600); //set size of window
        setLocationRelativeTo(null); //set the location of window relative to the current component c (in this case the component c is 'null' so that we're setting the window is centered on the screen)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //set default close
        setVisible(true);
        goBack.addActionListener(e -> {
            setVisible(false);
            new Admin();
        });
        refresh.addActionListener(e -> {
            setVisible(false);
            new ShowUserList();
        });
    }

    public static void main(String[] args) {
        new ShowUserList();
    }

    public void readDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // connect to database
            Connection conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);

            // crate statement
            Statement stmt = conn.createStatement();

            // get data from table 'status'
            ResultSet rs = stmt.executeQuery("select * from status"); // get status' data

            // create a table model with the appropriate column headers and with 0 rows (to start with)
            String[] columnNames = {"User ID", "Status", "Time"};
            DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

            while (rs.next()) {
                String idUser = rs.getString("iduser");
                String status = rs.getString("is_online");
                String time = rs.getString("time");

                if (status.equals("1")) {
                    status = "true";
                } else {
                    status = "false";
                }

                // create a single array of one row's worth of data
                String[] data = {idUser, status, time};

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
        this.mainTitle = new JLabel("User list");
        readDatabase();
    }
}
