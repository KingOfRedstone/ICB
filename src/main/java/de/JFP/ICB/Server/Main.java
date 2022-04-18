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
    }

}
