package server;

import java.io.IOException;
import java.util.Scanner;

public class Launch_server {
    public static void GO(){
        System.out.print("Enter the free port : ");
        Scanner scanner = new Scanner(System.in);
        int port = Integer.parseInt(scanner.nextLine());
        System.out.println("Connection ... ... ");
        SocketServer socketServer = new SocketServer();
        try {
            Thread mouseAction = new Thread(socketServer);
            mouseAction.start();                              // Au cas d`une action de Souris
            socketServer.launc_Server(port);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            System.out.println("--------------------- END ----------------------------\n");
            GO();
        }
    }
}
