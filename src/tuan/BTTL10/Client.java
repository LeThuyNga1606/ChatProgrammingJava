package tuan.BTTL10;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
//import java.util.Scanner;

public class Client extends JFrame implements ActionListener {
    private JLabel userName;
    private JLabel status;
    private JTextArea display;
    private JScrollPane scroll;
    private String messageToSend;
    private String messageData = "";
    private JPanel msgDisplayPanel;
    private JTextArea textArea;
    private JButton sendMsg;
    private JPanel titlePanel;
    private Socket socket;
    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;
    private String username = "An";

    Client() {
        // Init the window
        this.setLayout(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Chat app");
        this.setSize(450, 600);
        this.setResizable(false);

        // Init the title panel
        titlePanel = new JPanel(null);
        titlePanel.setBackground(new Color(10, 94, 84));
        titlePanel.setBounds(0, 0, 435, 70);

        // Username display on the top of window
        userName = new JLabel(username.toUpperCase());
        userName.setBounds(200, 25, 150, 20);
        userName.setForeground(Color.WHITE);
        userName.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        titlePanel.add(userName);


        // Status of user
        status = new JLabel("Active Now");
        status.setBounds(370, 50, 100, 12);
        status.setForeground(Color.WHITE);
        status.setFont(new Font("SAN_SERIF", Font.ITALIC, 12));
        titlePanel.add(status);


        // Messages viewport
        display = new JTextArea();
        display.setFont(new Font("SAN_SERIF", Font.PLAIN, 15));
        display.setLineWrap(true);
        display.setWrapStyleWord(true);
        display.setEditable(false); // set textArea non-editable

        scroll = new JScrollPane(display);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setBounds(10, 80, 415, 390);

        // Init typing panel
        textArea = new JTextArea();
        textArea.setBounds(10, 475, 315, 75);
        textArea.setFont(new Font("SAN_SERIF", Font.PLAIN, 15));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        sendMsg = new JButton("Send");
        sendMsg.setBounds(340, 475, 80, 75);
        sendMsg.addActionListener(e -> {
            messageToSend = textArea.getText();
            textArea.setText("");

            messageData += username + ": " + messageToSend + "\n";
            display.setText(messageData);
        });

        // Add components to this(window)
        this.add(titlePanel);
        this.add(scroll);
        this.add(textArea);
        this.add(sendMsg);
        this.setVisible(true);
    }

    public Client(Socket socket, String username) {
        try {
            this.socket = socket;
            new Client();
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedReader = new BufferedReader((new InputStreamReader(socket.getInputStream())));
            this.username = username;
        } catch (IOException e) {
            closeEverything(socket, bufferedWriter, bufferedReader);
        }
    }

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 5678);
        Client client = new Client(socket, "An");
        client.listenForMessage();
        client.sendMessage();

    }

    public void sendMessage() {
        try {
            bufferedWriter.write(username);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            while (socket.isConnected()) {
                bufferedWriter.write(username + ": " + messageToSend);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            closeEverything(socket, bufferedWriter, bufferedReader);
        }
    }

    public void listenForMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String msgFromGroupChat;

                while (socket.isConnected()) {
                    try {
                        msgFromGroupChat = bufferedReader.readLine();
                        messageData += msgFromGroupChat;
                        display.setText(messageData);
                    } catch (IOException e) {
                        closeEverything(socket, bufferedWriter, bufferedReader);
                    }
                }
            }
        }).start();
    }

    public void closeEverything(Socket socket, BufferedWriter bufferedWriter, BufferedReader bufferedReader) {
        try {
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
