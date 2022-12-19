package server;

import java.io.IOException;
import java.util.Scanner;

public class Launch_server implements Runnable{
    SocketServer socketServer;
    int port;
    String message;

    public void setSocketServer(SocketServer socketServer) {this.socketServer = socketServer;}

    public void setPort(int port) {this.port = port;}

    public void setMessage(String message) {this.message = message;}

    public SocketServer getSocketServer() {return socketServer;}

    public int getPort() {return port;}

    public String getMessage() {
        System.out.println("");
        return message;
    }

    public void GO(int port) throws Exception {
        this.setSocketServer(new SocketServer());
        try {
            Thread mouseAction = new Thread(socketServer);
            mouseAction.start();                              // Au cas d`une action de Souris
            socketServer.launc_Server(port);
        } catch (IOException e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            this.GO(this.getPort());
        } catch (Exception e) {
            this.setMessage(e.getMessage());
            return;
        }
    }
}
