package de.JFP.ICB.Server;

import java.io.DataInputStream;
import java.net.Socket;

public class ClientReadThread extends Thread {

    private Socket socket;

    public ClientReadThread(Socket socket)  {
        this.socket = socket;
    }

    public void run() {
        try {
            DataInputStream in = new DataInputStream(socket.getInputStream());
            while (!socket.isClosed()) {
                String line = in.readUTF();
                if (!line.equals("")) {
                    //Send to all
                    for (Socket connectedSocket : Server.connectedSockets) {
                        Server.sendRawMessage(connectedSocket, line);
                    }
                }
            }
        }catch (Exception e) {
            Server.connectedSockets.remove(socket);
        }
    }
}
