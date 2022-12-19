package server;

import system.MouseAction;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer implements Runnable{
    public final static String width = String.valueOf((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth());
    public final static String heigth = String.valueOf(Toolkit.getDefaultToolkit().getScreenSize().getHeight());

    public final static String IMAGE_TYPE_JPEG = "jpeg";    // non modifiable et pas besion d`instance pour les appelers
    public final static String IMAGE_TYPE_GIF = "gif";
    public final static String IMAGE_TYPE_PNG = "png";

    public int SOCKET_PORT;      // 1522
    Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
    BufferedImage capture;
    ServerSocket serverSocket;
    Socket client;
    MouseAction mouseAction;

    public void setSOCKET_PORT(int SOCKET_PORT) {this.SOCKET_PORT = SOCKET_PORT;}

    public void setCapture(BufferedImage capture) {this.capture = capture;}

    public void setServerSocket(ServerSocket serverSocket) {this.serverSocket = serverSocket;}

    public void setClient(Socket client) {this.client = client;}

    public void setMouseAction(MouseAction mouseAction) {this.mouseAction = mouseAction;}

    public int getSOCKET_PORT() {return SOCKET_PORT;}

    public BufferedImage getCapture() {return capture;}

    public ServerSocket getServerSocket() {return serverSocket;}

    public Socket getClient() {return client;}

    public void launc_Server(int port) throws IOException {
        this.setSOCKET_PORT(port);
        try {
            this.setMouseAction(new MouseAction());                         // Mouse which does the action
            this.setServerSocket(new ServerSocket(this.getSOCKET_PORT()));  // My port
            this.setClient(this.getServerSocket().accept());                // Accept client
            this.send_Screen_Resolution();                                  // My screen size
            this.send_My_Screen();                                          // Envoie du capture a l` infinis
        } catch (Exception e) {
            throw new EOFException(e.getMessage());
        } finally {
            if (serverSocket != null) serverSocket.close();
        }
    }

    public void send_Screen_Resolution(){
        ObjectOutputStream oos = null;
        try{
            oos = new ObjectOutputStream(this.getClient().getOutputStream());
            String message = this.width+"/"+this.heigth;
            oos.flush();
            oos.writeObject(message);
            oos.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void send_My_Screen() throws IOException {
        try {
            OutputStream outputStream = null;
            Robot robot = new Robot();
            while (true) {
                this.setCapture(robot.createScreenCapture(screenRect));
                outputStream = client.getOutputStream();
                outputStream.flush();
                ImageIO.write(this.getCapture(), this.IMAGE_TYPE_PNG, outputStream);
                outputStream.flush();
                Thread.sleep(4);
            }
        }catch (Exception e){
//            e.printStackTrace();
            if(client.isClosed() == false)client.close();
            return;
        }
    }

    @Override
    public void run() {                                                 // Au cas d`une reception de donnnes
        try {
            while (true) {
                System.out.print("");
                Thread thread = null;
                if (client != null && client.getInputStream() != null) {
                    ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
                    String message = (String) ois.readObject();

                    int[] bxywh = this.bxywh(message);
                    mouseAction.setX(this.x_position(bxywh));
                    mouseAction.setY(this.y_position(bxywh));
                    mouseAction.setButton(this.button(bxywh));
                    thread = new Thread(mouseAction);
                    thread.start();
                }
            }
        }catch (Exception e){
            try {
                if(client.isClosed() == false)client.close();               // deconnexion
                return;
            } catch (IOException ex) {
            }
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
        int heigth = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        return heigth*bxywh[2]/bxywh[4];
    }
    public int button(int[] bxywh){
        return bxywh[0];
    }
}