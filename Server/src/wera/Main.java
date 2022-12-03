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
            Thread mouseAction = new Thread(socketServer);
            mouseAction.start();                              // Au cas d`une action de Souris
            socketServer.launc_Server(1522);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}