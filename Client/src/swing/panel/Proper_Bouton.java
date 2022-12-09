package swing.panel;

import client.ClientMultipleServer;

import javax.swing.*;

public class Proper_Bouton extends JButton {
    ClientMultipleServer clientMultipleServer;

    public void setClientMultipleServer(ClientMultipleServer clientMultipleServer) {
        this.clientMultipleServer = clientMultipleServer;
    }

    public ClientMultipleServer getClientMultipleServer() {
        return clientMultipleServer;
    }

    public Proper_Bouton(String text) {
        super(text);
    }
    public Proper_Bouton(ClientMultipleServer clientMultipleServer,Thread thread) {
        this.setClientMultipleServer(clientMultipleServer);
    }
}
