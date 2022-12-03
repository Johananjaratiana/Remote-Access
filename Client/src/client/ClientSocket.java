package client;

import swing.Fenetre;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;

public class ClientSocket implements Runnable{
    private int server_width;
    private int server_heigth;
    private int SOCKET_PORT;      // you may change this    // 1522
    private String SERVER;        // localhost              //  = "127.0.0.1"
    // you may change this, I give a different name because i don't want to
    // overwrite the one used by server...
    Fenetre fenetre;
    Socket serverSocket;
    String message;

    public void setServer_heigth(int server_heigth) {this.server_heigth = server_heigth;}

    public void setServer_width(int server_width) {this.server_width = server_width;}

    public void setSOCKET_PORT(int SOCKET_PORT) {this.SOCKET_PORT = SOCKET_PORT;}

    public void setSERVER(String SERVER) {this.SERVER = SERVER;}

    public void setFenetre(Fenetre fenetre) {this.fenetre = fenetre;}

    public void setServerSocket(Socket serverSocket) {this.serverSocket = serverSocket;}

    public void setMessage(String message) {this.message = message;}



    public int getServer_width() {return server_width;}

    public int getServer_heigth() {return server_heigth;}

    public int getSOCKET_PORT() {return SOCKET_PORT;}

    public String getSERVER() {return SERVER;}

    public Fenetre getFenetre() {return fenetre;}

    public Socket getServerSocket() {return serverSocket;}

    public String getMessage() {return message;}

    public void set_Screen_Resolution(Socket serverSocket){
        ObjectInputStream ois = null;
        try{
            ois = new ObjectInputStream(serverSocket.getInputStream());
            String dimension = (String) ois.readObject();
            int[] width_heigth =  this.width_heigth(dimension);
            this.setServer_width(width_heigth[0]);
            this.setServer_heigth(width_heigth[1]);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public int[] width_heigth(String dimension){
        String[] temp = dimension.split("/");
        int[] answer = new int[2];
        if(temp[0].contains(".") == true)temp[0] = temp[0].substring(0,temp[0].indexOf("."));
        if(temp[1].contains(".") == true)temp[1] = temp[1].substring(0,temp[1].indexOf("."));
        answer[0] = Integer.parseInt(temp[0]);
        answer[1] = Integer.parseInt(temp[1]);
        return answer;
    }
    public BufferedImage get_Server_Screen(Socket serverSocket) {
        BufferedImage image = null;
        try {
            InputStream inputStream = serverSocket.getInputStream();                            // read_Image
            if(inputStream != null)image = ImageIO.read(inputStream);
            return image;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return image;
        }
    }
    public void connected () throws Exception {
        this.setSERVER(this.getSERVER());
        this.setSOCKET_PORT(this.getSOCKET_PORT());
        try {
            this.setServerSocket(new Socket(SERVER, SOCKET_PORT));
            this.refreshCapture();
        }catch (Exception e){
            throw new Exception("Connection error");
        } finally{
            if (serverSocket != null) serverSocket.close();
        }
    }
    public void refreshCapture(){
        try {
            while (true) {
                if (this.getFenetre() == null) {                                                       // initialisation du fenetre
                    this.setFenetre(new Fenetre(this));
                }                                                                                      // reactualiser avec l` image recu
                else this.getFenetre().getImagePanel().refresh(this.get_Server_Screen(this.getServerSocket()));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(this.getServerSocket().getOutputStream());
            String message = this.getMessage();                                                 // send Mouse_position with Thread ==> dans MouseXY()
            oos.flush();
            oos.writeObject(message);
            oos.flush();
            System.out.println(message);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}