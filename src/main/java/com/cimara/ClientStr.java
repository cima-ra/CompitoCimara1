package com.cimara;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class ClientStr {
    private int porta;
    private String indirizzoServer;
    private Socket server;
    private BufferedReader inDalServer;
    private DataOutputStream outNelServer;

    public ClientStr(int porta, String indirizzoServer) {
        this.porta = porta;
        this.indirizzoServer = indirizzoServer;
    }

    public void connetti() {

        try {
            server = new Socket(indirizzoServer, porta);
            inDalServer = new BufferedReader(new InputStreamReader(server.getInputStream()));
            outNelServer = new DataOutputStream(server.getOutputStream());

        } catch (Exception e) {
            System.out.println("Errore nella connessione col server\n");
            System.err.println(e.getMessage());
        }

    }

    public void comunica() {
        System.out.println("Inizio comunicazione");
        Scanner tastiera = new Scanner(System.in);
        String input;

        while (true) {

            try {
                System.out.println(inDalServer.readLine());
                input = tastiera.nextLine();
                outNelServer.writeBytes(input + '\n');

                if (input.equalsIgnoreCase("Finisci")) {
                    break;
                }

            } catch (Exception e) {
                System.out.println("Errore\n");
                System.err.println(e.getMessage());
            }
        }
        tastiera.close();
        chiudi();

    }

    public void chiudi() {
        try {
            inDalServer.close();
            outNelServer.close();
            server.close();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }
}
