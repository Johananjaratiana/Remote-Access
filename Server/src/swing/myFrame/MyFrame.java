package swing.myFrame;

import swing.myPanel.MyPanel;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    public final static int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    public final static int heigth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    MyPanel myPanel;

    public void setMyPanel(MyPanel myPanel) {this.myPanel = myPanel;}

    public MyPanel getMyPanel() {return myPanel;}

    public MyFrame() throws HeadlessException {
        this.setLayout(null);
        this.setSize(width/2,heigth/3);

        this.setMyPanel(new MyPanel(this.getWidth()/4,10,this.getWidth()/2,this.getHeight()*8/10));
        this.add(this.getMyPanel());


        this.setFocusable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
