package client;

import swing.frame.Fenetre;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;

public class ClientSocket implements Runnable{
    public final static String IMAGE_TYPE_PNG = "png";
    public final static String IMAGE_TYPE_JPEG = "jpeg";
    public final static String IMAGE_TYPE_GIF = "gif";

    private int server_width;
    private int server_heigth;
    private int SOCKET_PORT;      // you may change this    // 1522
    private String SERVER;        // localhost              //  = "127.0.0.1"
    private String reconnaissance;
    // you may change this, I give a different name because i don't want to
    // overwrite the one used by server...
    Fenetre fenetre;
    Socket serverSocket;
    String message;

    public void setServer_heigth(int server_heigth) {this.server_heigth = server_heigth;}

    public void setServer_width(int server_width) {this.server_width = server_width;}

    public void setSOCKET_PORT(int SOCKET_PORT) {this.SOCKET_PORT = SOCKET_PORT;}

    public void setSERVER(String SERVER) {this.SERVER = SERVER;}

    public void setReconnaissance(String reconnaissance) {this.reconnaissance = reconnaissance;}

    public void setFenetre(Fenetre fenetre) {this.fenetre = fenetre;}

    public void setServerSocket(Socket serverSocket) {this.serverSocket = serverSocket;}

    public void setMessage(String message) {this.message = message;}


    public int getServer_width() {return server_width;}

    public int getServer_heigth() {return server_heigth;}

    public int getSOCKET_PORT() {return SOCKET_PORT;}

    public String getSERVER() {return SERVER;}

    public String getReconnaissance() {return reconnaissance;}

    public Fenetre getFenetre() {return fenetre;}

    public Socket getServerSocket() {return serverSocket;}

    public String getMessage() {return message;}

    public void set_Screen_Resolution(Socket serverSocket){
        if(serverSocket.isClosed() == true)return;
        ObjectInputStream ois = null;
        try{
            ois = new ObjectInputStream(serverSocket.getInputStream());
            String dimension = (String) ois.readObject();
            int[] width_heigth =  this.width_heigth(dimension);
            if(width_heigth[0] > Fenetre.width || width_heigth[1] > Fenetre.heigth){
                this.setServer_width(Fenetre.width);
                this.setServer_heigth(Fenetre.heigth);
            }else {
                this.setServer_width(width_heigth[0]);
                this.setServer_heigth(width_heigth[1]);
            }
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
            ImageIO.getImageReadersByFormatName(this.IMAGE_TYPE_PNG);
            if(inputStream != null)image = ImageIO.read(inputStream);
            return image;
        } catch (Exception e) {
            this.getFenetre().setVisible(false);
            System.gc();
            if(serverSocket != null && serverSocket.isClosed() == true)return null;
        }finally {
            return image;
        }
    }
    public void connected () throws Exception {
        this.setSERVER(this.getSERVER());
        this.setSOCKET_PORT(this.getSOCKET_PORT());
        try {
            System.out.print("");
//            System.out.println(SERVER+" || "+SOCKET_PORT);
            this.setServerSocket(new Socket(SERVER, SOCKET_PORT));
            this.setFenetre();
        }catch (Exception e){
            throw new Exception("Connection error");
        } finally{
            if (serverSocket != null) serverSocket.close();
        }
    }
    public void setFenetre(){
        while (true) {
            try {
                if (this.getFenetre() == null) {                                                       // initialisation du fenetre
                    this.setFenetre(new Fenetre(this));
                }                                                                                      // reactualiser avec l` image recu
                else {
                    this.getFenetre().getImagePanel().refresh(this.get_Server_Screen(this.getServerSocket()));
                    if(serverSocket.isClosed() == true)return;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public void stop_access() throws IOException {
        this.getServerSocket().close();
        this.getFenetre().setVisible(false);
        this.setFenetre(null);
        System.gc();
    }

    @Override
    public void run() {
        ObjectOutputStream oos = null;
        try {
            System.out.print("");
            oos = new ObjectOutputStream(this.getServerSocket().getOutputStream());
            String message = this.getMessage();                                                 // send Mouse_position with Thread ==> dans MouseXY()
//            System.out.println(message);
            oos.flush();
            oos.writeObject(message);
            oos.flush();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}