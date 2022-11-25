package wera;

import client.ClientSocket;

import java.io.IOException;
import java.net.Inet4Address;

public class Main {
    public static void main(String[] args) throws IOException {
        ClientSocket clientSocket = new ClientSocket();
        try {
            clientSocket.main(Inet4Address.getLoopbackAddress().getHostName(), 1522);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}