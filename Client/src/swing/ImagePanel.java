package swing;

import listener.MouseXY;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.Socket;

public class ImagePanel extends JPanel {

    public BufferedImage bufferedImage;
    MouseXY mouseXY;

    public BufferedImage getBufferedImage() {return bufferedImage;}
    public MouseXY getMouseXY() {return mouseXY;}

    public void setBufferedImage(BufferedImage bufferedImage) {this.bufferedImage = bufferedImage;}
    public void setMouseXY(MouseXY mouseXY) {this.mouseXY = mouseXY;}

    public ImagePanel(Socket server,BufferedImage bufferedImage, int width, int heigth) throws IOException, InterruptedException {
        this.setBounds(0, 0, width, heigth);
        this.setMouseXY(new MouseXY(server));
        this.addMouseListener(this.getMouseXY());
        this.addMouseMotionListener(this.getMouseXY());
        this.refresh(bufferedImage);
    }

    public void refresh(BufferedImage bufferedImage) throws IOException, InterruptedException {
        this.setBufferedImage(bufferedImage);
        Graphics g = this.getGraphics();
        if(g != null){
            g.drawImage(this.getBufferedImage(),0,0,null);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }
}
