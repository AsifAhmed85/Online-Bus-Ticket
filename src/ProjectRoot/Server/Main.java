package ProjectRoot.Server;

import ProjectRoot.Server.Networking.ClientThread;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import util.NetworkUtil;

public class Main {
    private ServerSocket serverSocket;

    public void setConnection() {
        try {
            serverSocket = new ServerSocket(22755);
            System.out.println("Server has started");
            while(true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("user connected");
                NetworkUtil nc = new NetworkUtil(clientSocket);
                new ClientThread(nc);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Main m = new Main();
        m.setConnection();
    }
}
