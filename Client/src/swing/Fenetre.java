package swing;


import client.ClientSocket;
import swing.panel.Menu;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Fenetre extends JFrame{
    public final static int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    public final static int heigth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();

    ImagePanel imagePanel;
    Menu menu;
    ClientSocket clientSocket;

    public void setImagePanel(ImagePanel imagePanel) {this.imagePanel = imagePanel;}
    public void setMenu(Menu menu) {this.menu = menu;}
    public void setClientSocket(ClientSocket clientSocket) {this.clientSocket = clientSocket;}

    public ImagePanel getImagePanel() {return imagePanel;}
    public Menu getMenu() {return menu;}
    public ClientSocket getClientSocket() {return clientSocket;}

    public Fenetre() throws HeadlessException, IOException {                                     // insert Server_data
        super("Remote Access");

        this.setMenu(new Menu(this.width,this.heigth));

        this.add(this.getMenu());

        this.setSize(this.width, this.heigth);
        this.setLayout(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public  Fenetre(ClientSocket clientSocket) {                                    // Le Meme clientSocket qui l` appelle
        this.setUndecorated(true);
        this.setClientSocket(clientSocket);

        this.setImagePanel(new ImagePanel());
        this.getImagePanel().launch(clientSocket);
        this.add(this.getImagePanel());

        this.setSize(clientSocket.getServer_width(), clientSocket.getServer_heigth());
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setVisible(true);                                 // How to disconnect with the server
    }
}
