package tuan.test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class TestingServer {
    public static ArrayList<Socket> listSK;
    private final int port;

    public TestingServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws IOException {
        TestingServer.listSK = new ArrayList<>();
        TestingServer server = new TestingServer(15797);
        server.execute();
    }

    private void execute() throws IOException {
        try (ServerSocket server = new ServerSocket(port)) {
            WriteServer write = new WriteServer();
            write.start();
            System.out.println("Server is listening...");
            //noinspection InfiniteLoopStatement

            while (true) {
                Socket socket = server.accept();
                System.out.println("Connected " + socket);
                TestingServer.listSK.add(socket);
                ReadServer read = new ReadServer(socket);
                read.start();
            }
        }
    }
}


class ReadServer extends Thread {
    private final Socket socket;

    public ReadServer(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            DataInputStream dis = new DataInputStream(socket.getInputStream());

            //noinspection InfiniteLoopStatement
            while (true) {
                String sms = dis.readUTF();
                if (sms.contains("exit")) {
                    TestingServer.listSK.remove(socket);
                    System.out.println("Diconnected " + socket);
                    dis.close();
                    socket.close();
                    continue; //Ngắt kết nối rồi
                }
                for (Socket item : TestingServer.listSK) {
                    if (item.getPort() != socket.getPort()) {
                        DataOutputStream dos = new DataOutputStream(item.getOutputStream());
                        dos.writeUTF(sms);
                    }
                }
                System.out.println(sms);
            }
        } catch (Exception e) {
            try {
                socket.close();
            } catch (IOException ex) {
                System.out.println("Disconnect from server");
            }
        }
    }
}

class WriteServer extends Thread {

    @Override
    public void run() {
        DataOutputStream dos;
        Scanner sc = new Scanner(System.in);

        //noinspection InfiniteLoopStatement
        while (true) {
            String sms = sc.nextLine();    //Đang đợi Server nhập dữ liệu
            try {
                for (Socket item : TestingServer.listSK) {
                    dos = new DataOutputStream(item.getOutputStream());
                    dos.writeUTF("Server: " + sms);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
