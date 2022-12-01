package client;

import swing.Fenetre;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.server.ServerCloneException;

public class ClientSocket implements Runnable{

    private int SOCKET_PORT;      // you may change this    // 1522
    private String SERVER;        // localhost              //  = "127.0.0.1"
    // you may change this, I give a different name because i don't want to
    // overwrite the one used by server...
    Fenetre fenetre;
    Socket serverSocket;

    public void setSOCKET_PORT(int SOCKET_PORT) {this.SOCKET_PORT = SOCKET_PORT;}
    public void setSERVER(String SERVER) {this.SERVER = SERVER;}
    public void setFenetre(Fenetre fenetre) {this.fenetre = fenetre;}
    public void setServerSocket(Socket serverSocket) {this.serverSocket = serverSocket;}

    public int getSOCKET_PORT() {return SOCKET_PORT;}
    public String getSERVER() {return SERVER;}
    public Fenetre getFenetre() {return fenetre;}
    public Socket getServerSocket() {return serverSocket;}

    public void connected (String server, int port) throws IOException {
        this.setSERVER(server);
        this.setSOCKET_PORT(port);
        try {
            this.setServerSocket(new Socket(SERVER, SOCKET_PORT));
            this.run();
        } finally{
            if (serverSocket != null) serverSocket.close();
        }
    }
    public BufferedImage screenshot(Socket serverSocket) {
        BufferedImage image = null;
        try {
            InputStream inputStream = serverSocket.getInputStream();
            if(inputStream != null)image = ImageIO.read(inputStream);
            return image;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return image;
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (fenetre == null) {
                    fenetre = new Fenetre(this.screenshot(this.getServerSocket()),this.getServerSocket());
                }
                else fenetre.refresh(this.screenshot(this.getServerSocket()));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}