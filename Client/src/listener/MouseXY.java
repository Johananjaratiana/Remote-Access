package listener;

import swing.Fenetre;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class MouseXY implements MouseListener, MouseMotionListener {
    Socket server;

    public Socket getServer() {return server;}
    public void setServer(Socket server) {this.server = server;}

    public MouseXY(Socket server) {
        this.setServer(server);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(this.getServer().getOutputStream());
            String message = e.getButton() + "/" + e.getX() + "/" + e.getY();
            message += "/"+Fenetre.width+"/"+Fenetre.heigth;
            oos.writeObject(message);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {
//        if(e.getButton() != 0)return;
//        ObjectOutputStream oos = null;
//        try {
//            oos = new ObjectOutputStream(this.getServer().getOutputStream());
//            String message = e.getButton() + "/" + e.getX() + "/" + e.getY();
//            message += "/"+Fenetre.width+"/"+Fenetre.heigth;
//            oos.writeObject(message);
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {}
}
