package wera;

import server.SocketServer;

import java.io.IOException;
import java.net.Inet4Address;

public class Main {
    public static void main(String[] args) {
        SocketServer socketServer = new SocketServer();
        try {
            socketServer.main(1522);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}