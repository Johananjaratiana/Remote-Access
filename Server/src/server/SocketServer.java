package server;

import system.MouseAction;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer implements Runnable{

    public final static String IMAGE_TYPE_JPEG = "jpeg";    // non modifiable et pas besion d`instance pour les appelers
    public final static String IMAGE_TYPE_GIF = "gif";
    public final static String IMAGE_TYPE_PNG = "png";

    public int SOCKET_PORT;      // 1522
    public String FILE_TO_SEND;  // C:\Users\Johan\Documents\Java\Naina\Remote Access\Server\ToSend\Ty le izy.txt
    Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
    BufferedImage capture;
    ServerSocket serverSocket;
    Socket client;

    public void setSOCKET_PORT(int SOCKET_PORT) {this.SOCKET_PORT = SOCKET_PORT;}
    public void setFILE_TO_SEND(String FILE_TO_SEND) {this.FILE_TO_SEND = FILE_TO_SEND;}
    public void setCapture(BufferedImage capture) {this.capture = capture;}
    public void setServerSocket(ServerSocket serverSocket) {this.serverSocket = serverSocket;}
    public void setClient(Socket client) {this.client = client;}

    public int getSOCKET_PORT() {return SOCKET_PORT;}
    public String getFILE_TO_SEND() {return FILE_TO_SEND;}
    public BufferedImage getCapture() {return capture;}
    public ServerSocket getServerSocket() {return serverSocket;}
    public Socket getClient() {return client;}

    public void screenShot(int port) throws IOException {
        this.setSOCKET_PORT(port);
        try {
            this.setServerSocket(new ServerSocket(this.getSOCKET_PORT()));
            this.setClient(this.getServerSocket().accept());
//            Une fois
            this.run();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) serverSocket.close();
        }
    }

    @Override
    public void run() {
        try {
            MouseAction mouseAction = new MouseAction();
            OutputStream outputStream = null;
            while (true) {
                this.setCapture(new Robot().createScreenCapture(screenRect));
                outputStream = client.getOutputStream();
                ImageIO.write(this.getCapture(), this.IMAGE_TYPE_PNG, outputStream);

//                if(client.getOutputStream() != null) {
//                    ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
//                    String message = (String) ois.readObject();
//                    int[] bxywh = this.bxywh(message);
////                System.out.println("Message:"+ message);
////                System.out.println("Values:"+ this.x_position(bxywh));
////                System.out.println("Values:"+ this.y_position(bxywh));
//                    mouseAction.click(this.x_position(bxywh), this.y_position(bxywh), this.button(bxywh));
//                }

//                Envoyer le souris par un thread

                outputStream.flush();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
//    [0] = Button {0,1,3}  //  [1,2] = (x,y){position}  //  [3,4] (w,h){width,height}
    public int[] bxywh(String mouseAction){
        String[] temp = mouseAction.split("/");
        int[] answer = new int[5];
        for(int a = 0 ; a < temp.length ; a++){
            answer[a] = Integer.parseInt(temp[a]);
        }
        return answer;
    }
    public int x_position(int[] bxywh){
        int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        return width*bxywh[1]/bxywh[3];
    }
    public int y_position(int[] bxywh){
        int heigth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        return heigth*bxywh[2]/bxywh[4];
    }
    public int button(int[] bxywh){
        return bxywh[0];
    }
}