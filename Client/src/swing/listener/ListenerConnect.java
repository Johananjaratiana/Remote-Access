package swing.listener;

import client.ClientSocket;

import swing.panel.Menu;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ListenerConnect implements Runnable , MouseListener {
    Menu menu;
    ClientSocket clientSocket;

    public void setMenu(Menu menu) {this.menu = menu;}

    public void setClientSocket(ClientSocket clientSocket) {this.clientSocket = clientSocket;}

    public ClientSocket getClientSocket() {return clientSocket;}

    public Menu getMenu() {return menu;}

    public ListenerConnect(Menu menu) {
        ClientSocket clientSocket = new ClientSocket();
        this.setClientSocket(clientSocket);
        this.setMenu(menu);
    }

    @Override
    public void run(){
        try {
            this.getClientSocket().connected();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        try {

            String server = this.getMenu().getAdresse_IP();
            int port = this.getMenu().getPort();
            this.getClientSocket().setSERVER(server);
            this.getClientSocket().setSOCKET_PORT(port);                              // start connection

            Thread connex = new Thread(this);
            connex.start();                                                           // start connection with Thread
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
