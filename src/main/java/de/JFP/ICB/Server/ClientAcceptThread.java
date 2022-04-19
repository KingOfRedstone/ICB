package de.JFP.ICB.Server;

import java.io.IOException;
import java.net.Socket;

public class ClientAcceptThread extends Thread {

    public void run() {
        while (Server.getServerSocket().isClosed()) {
            try {
                Socket client = Server.getServerSocket().accept();
                Server.connectedSockets.add(client);
                Thread t = new Thread(new ClientReadThread(client));
                t.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
