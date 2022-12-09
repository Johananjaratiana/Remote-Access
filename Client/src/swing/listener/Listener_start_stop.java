package swing.listener;

import swing.panel.Proper_Bouton;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class Listener_start_stop implements MouseListener, Runnable{
    Proper_Bouton proper_bouton;
    boolean isAlive;

    public void setProper_bouton(Proper_Bouton proper_bouton) {this.proper_bouton = proper_bouton;}

    public void setAlive(boolean alive) {isAlive = alive;}

    public Proper_Bouton getProper_bouton() {return proper_bouton;}

    public boolean getAlive(){return isAlive;}

    public Listener_start_stop(Proper_Bouton proper_bouton) {
        this.setAlive(false);
        this.setProper_bouton(proper_bouton);
    }

    public void stop() throws IOException {
        this.getProper_bouton().getClientMultipleServer().stop_access();
        this.getProper_bouton().setVisible(false);
        this.setProper_bouton(null);
    }

    @Override
    public void run(){
        try {
            this.getProper_bouton().getClientMultipleServer().connected();                                   // Start the latest connected
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(this.isAlive == false){
            try {
                this.setAlive(true);
                Thread thread = new Thread(this);
                thread.start();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }else{
            try {
                this.stop();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
