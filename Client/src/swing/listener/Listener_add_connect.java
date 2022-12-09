package swing.listener;

import client.ClientMultipleServer;

import swing.frame.Fenetre;
import swing.panel.Menu;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

public class Listener_add_connect implements  MouseListener {
    Menu menu;
    Vector<ClientMultipleServer> PC = new Vector<>();
    Vector<Thread> threads = new Vector<>();
    Fenetre fenetre_principale;
    Vector<Fenetre> fenetre = new Vector<>();

    public void setFenetre_principale(Fenetre fenetre) {this.fenetre_principale = fenetre;}

    public void setMenu(Menu menu) {this.menu = menu;}

    public Vector<ClientMultipleServer> getPC() {return PC;}

    public Vector<Thread> getThreads() {return threads;}

    public Fenetre getFenetre_principale() {return fenetre_principale;}

    public Vector<Fenetre> getFenetre() {return fenetre;}

    public Menu getMenu() {return menu;}

    public Listener_add_connect(Menu menu, Fenetre fenetre) {
        this.setMenu(menu);
        this.setFenetre_principale(fenetre);
    }

    public ClientMultipleServer on_another_connection(){
        return new ClientMultipleServer();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            this.PC.add(this.on_another_connection());                        // new_Client_Socket
            String server = this.getMenu().getAdresse_IP();
            int port = this.getMenu().getPort();
            String reconaisse = this.getMenu().getReconnaisance();
            PC.lastElement().setSERVER(server);
            PC.lastElement().setSOCKET_PORT(port);
            PC.lastElement().setReconnaissance(reconaisse);

            this.getMenu().update_connected();                                // add_on_menu
            this.getFenetre_principale().add_one_Connected(this.getMenu().getBouton_cn_dscn().lastElement());
            this.getFenetre_principale().repaint();

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
