package swing.listener;

import server.Launch_server;
import swing.myPanel.MyPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class ListenerStart implements MouseListener {
    MyPanel myPanel;
    Thread newLauncher;
    Launch_server launch_server;

    public void setMyPanel(MyPanel myPanel) {this.myPanel = myPanel;}

    public void setLaunch_server(Launch_server launch_server) {this.launch_server = launch_server;}

    public void setNewLauncher() {this.newLauncher = new Thread(this.getLaunch_server());}

    public MyPanel getMyPanel() {return myPanel;}

    public Thread getNewLauncher() {return newLauncher;}

    public Launch_server getLaunch_server() {return launch_server;}

    public ListenerStart(MyPanel myPanel) {
        this.setLaunch_server(new Launch_server());
        this.setNewLauncher();
        this.setMyPanel(myPanel);
    }
    public void stop() throws IOException {
        this.getNewLauncher().stop();
        this.getLaunch_server().getSocketServer().getServerSocket().close();
        this.setNewLauncher();
        this.getMyPanel().getStart_server().setText("start");

        this.getMyPanel().changeMessage(this.getLaunch_server().getMessage());
        this.getLaunch_server().setMessage(null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(this.getMyPanel().getStart_server().getText().equalsIgnoreCase("start") == true){
            int port;
            try{
                port = this.isAnyPort();                            // check port
            } catch (Exception ex) {
                this.getMyPanel().changeMessage(ex.getMessage());
                return;
            }
            this.getMyPanel().changeMessage("");
            this.getLaunch_server().setPort(port);
            this.getNewLauncher().start();
            this.getMyPanel().getStart_server().setText("stop");
        }else if(this.getMyPanel().getStart_server().getText().equalsIgnoreCase("stop") == true) {
            try {                                           // stop
                this.stop();
            } catch (IOException ex) {
                this.getMyPanel().changeMessage(ex.getMessage());
            }
        }
    }
    public int isAnyPort()throws Exception{
        try{
            return Integer.parseInt(this.getMyPanel().getPort().getText());
        }catch (Exception e){
            throw new Exception("Please insert the rigth port");
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
}
