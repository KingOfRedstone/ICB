package de.JFP.ICB.API;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ICB {

    private static Socket socket;
    private static Thread readThread;

    public static void connect(String host, int port) throws Exception {
        if (socket == null) {
            socket = new Socket(host,port);
            readThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        DataInputStream in = new DataInputStream(socket.getInputStream());
                        while (!socket.isClosed()) {
                            String line = in.readUTF();
                            if (!line.equals("")) {
                                ChannelManager.onMessage(line);
                            }
                        }
                    } catch (IOException e) {}
                }
            });
            readThread.start();
        }else {
            throw new Exception("Socket is Already connected!");
        }
    }

    public static void disconnect() throws Exception {
        if (socket != null) {
            socket.close();
            socket = null;
            readThread.stop();
            readThread = null;
        }else {
            throw new Exception("Socket is Already disconnected!");
        }
    }

}
