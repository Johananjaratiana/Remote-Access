package swing;


import listener.MouseXY;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.Socket;

public class Fenetre extends JFrame {
    public final static int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    public final static int heigth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    JFrame f;
    ImagePanel imagePanel;

    public void setImagePanel(ImagePanel imagePanel) {this.imagePanel = imagePanel;}
    public void setF(JFrame f) {this.f = f;}

    public ImagePanel getImagePanel() {return imagePanel;}
    public JFrame getF() {return f;}

    public Fenetre(BufferedImage image, Socket server) throws HeadlessException, IOException, InterruptedException {
        f = new JFrame("Remote Access");
        this.setImagePanel(new ImagePanel(server,image,this.width, this.heigth));
        f.add(this.getImagePanel());
        f.setSize(this.width, this.heigth);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public void refresh(BufferedImage image) throws IOException, InterruptedException {
        this.getImagePanel().refresh(image);
    }
}
