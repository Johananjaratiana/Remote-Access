package wera;

import client.ClientSocket;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ClientSocket clientSocket = new ClientSocket();
        try {
            clientSocket.connected("192.168.43.156", 1522);
//            clientSocket.connected("localhost", 1522);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}