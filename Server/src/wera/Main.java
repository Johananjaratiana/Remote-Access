package wera;

import server.SocketServer;
import system.MouseAction;

import java.awt.*;
import java.io.IOException;
import java.util.concurrent.Executor;

public class Main {
    public static void main(String[] args) throws Exception{
        SocketServer socketServer = new SocketServer();
        try {
            socketServer.screenShot(1522);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}