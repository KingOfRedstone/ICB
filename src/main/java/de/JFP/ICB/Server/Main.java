package de.JFP.ICB.Server;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        String argsLine = "";
        for (String arg : args) {
            argsLine = argsLine + arg + " ";
        }
        argsLine = argsLine.substring(0, argsLine.length() - 1);
        if (argsLine.contains("noServer")) {
            //API
            // |-> Stop running
            System.exit(0);
        }
        //Server Start
        int port = 4444; //Default port
        for (String arg : args) {
            if (arg.startsWith("port=")) {
                try {
                    port = Integer.parseInt(arg.replace("port=", ""));
                }catch (Exception e) {}
            }
        }
        try {
            Server.startServer(port);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Server.stopServer();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }
        }));
    }

}
