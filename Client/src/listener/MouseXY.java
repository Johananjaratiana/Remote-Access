package listener;

import client.ClientSocket;
import swing.frame.Fenetre;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseXY implements MouseListener, MouseMotionListener {
    ClientSocket clientSocket;

    public void setClientSocket(ClientSocket clientSocket) {this.clientSocket = clientSocket;}

    public ClientSocket getClientSocket() {return clientSocket;}

    public MouseXY(ClientSocket clientSocket) {
        this.setClientSocket(clientSocket);
    }

    @Override
    public void mouseClicked(MouseEvent e){
        String message = e.getButton() + "/" + e.getX() + "/" + e.getY();
        message += "/"+Fenetre.width+"/"+Fenetre.heigth;

        this.getClientSocket().setMessage(message);

        Thread sendMouseClicked = new Thread(clientSocket);

        sendMouseClicked.start();
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
    }

    @Override
    public void mouseMoved(MouseEvent e) {}
}
