package swing;

import client.ClientSocket;
import swing.listener.MouseXY;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImagePanel extends JPanel {
    public BufferedImage bufferedImage;
    MouseXY mouseXY;

    public BufferedImage getBufferedImage() {return bufferedImage;}

    public MouseXY getMouseXY() {return mouseXY;}

    public void setBufferedImage(BufferedImage bufferedImage) {this.bufferedImage = bufferedImage;}

    public void setMouseXY(MouseXY mouseXY) {this.mouseXY = mouseXY;}

    public ImagePanel(){}

    public void launch(ClientSocket clientSocket) {
        this.setMouseXY(new MouseXY(clientSocket));                                                  // pour les coordonnes du souris
        this.addMouseListener(this.getMouseXY());                                                    // ajout du traceur des mouvements
        this.addMouseMotionListener(this.getMouseXY());

        clientSocket.set_Screen_Resolution(clientSocket.getServerSocket());                          // La resolution du Server
        this.setBounds(0, 0, clientSocket.getServer_width(), clientSocket.getServer_heigth());

        BufferedImage image = clientSocket.get_Server_Screen(clientSocket.getServerSocket());
        this.refresh(image);
    }

    public void refresh(BufferedImage bufferedImage) {                                                 // rafraichissement du nouvelle capture
        this.setBufferedImage(bufferedImage);
        Graphics2D g2 = (Graphics2D) this.getGraphics();
        if(g2 != null && this.getBufferedImage() != null){
//            Image scaled = this.getBufferedImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
            g2.drawImage(this.getBufferedImage(),0,0,this);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }
}
