package de.JFP.ICB.Server;

import javax.xml.crypto.Data;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private static ServerSocket serverSocket;
    public static List<Socket> connectedSockets = new ArrayList<>();

    public static void startServer(int port) throws Exception {
        if (serverSocket == null) {
            serverSocket = new ServerSocket(port);
            //Start Thread
            Thread t = new Thread(new ClientAcceptThread());
            t.start();
        }else {
            throw new Exception("Server already started!");
        }
    }

    public static void stopServer() throws Exception {
        if (serverSocket != null) {
            serverSocket.close();
            serverSocket = null;
        }else {
            throw new Exception("Server is not running!");
        }
    }

    public static ServerSocket getServerSocket() {
        return serverSocket;
    }

    public static void sendRawMessage(Socket socket, String message) {
        try {
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF(message);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
