package com.cimara;

public class App{

    public static void main(String[] args) {
        ClientStr client = new ClientStr(5101, "localhost");

        client.connetti();
        client.comunica();
    }

}
