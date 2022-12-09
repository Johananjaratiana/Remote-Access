package swing.panel;

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
        this.addMouseListener(this.getMouseXY());                                                    // ajout click de souris
//        this.addMouseMotionListener(this.getMouseXY());                                              // ajout drag with mouse

        clientSocket.set_Screen_Resolution(clientSocket.getServerSocket());                          // La resolution du Server
        this.setBounds(0, 0, clientSocket.getServer_width(), clientSocket.getServer_heigth());

        BufferedImage image = clientSocket.get_Server_Screen(clientSocket.getServerSocket());
        this.refresh(image);

        this.validate();
        this.setFocusable(true);
    }

    public void refresh(BufferedImage bufferedImage) {                                                 // rafraichissement du nouvelle capture
        Graphics2D g2 = (Graphics2D) this.getGraphics();
        if(g2 != null && bufferedImage != null){
            System.out.print("");
            this.setBufferedImage(bufferedImage);
            g2.drawImage(this.getBufferedImage(),0,0,this);
        }
        if(g2 != null && bufferedImage == null) {
            System.out.print("");
            g2.drawImage(this.getBufferedImage(),0,0,this);
            return;
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }
}
