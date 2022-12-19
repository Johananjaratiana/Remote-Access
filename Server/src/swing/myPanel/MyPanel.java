package swing.myPanel;

import swing.listener.ListenerStart;

import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {
    JTextField port;
    JButton start_server;
    JLabel message;

    public void setPort(JTextField port) {this.port = port;}

    public void setStart_server(JButton start_server) {this.start_server = start_server;}

    public void setMessage(JLabel message) {this.message = message;}

    public JButton getStart_server() {return start_server;}

    public JTextField getPort() {return port;}

    public JLabel getMessage() {return message;}

    public MyPanel(int x, int y , int width , int heigth) {
        this.setLayout(null);
        this.setBackground(new Color(218, 218, 218, 137));
        this.setLocation(x,y);
        this.setSize(width,heigth);

        int font_size = 24;
        int margin_v = heigth/10;

        this.add(this.constructLabel(width/4,margin_v,width/2,margin_v,"PORT NUMBER",font_size));
        this.setPort(this.construct_TextField(width/4,margin_v*3,width/2,margin_v,"1522",font_size));
        this.add(this.getPort());

//        Message
        this.setMessage(this.constructLabel(width/4,margin_v*4,width/2,margin_v,"",font_size));
        this.getMessage().setForeground(Color.red);
        this.add(this.getMessage());

        this.setStart_server(this.constructButton(width/4,margin_v*7,width/2,margin_v,"start",font_size));
        this.getStart_server().addMouseListener(new ListenerStart(this));
        this.add(this.getStart_server());

        this.setVisible(true);
    }

    public JButton constructButton(int x , int y, int width, int heigth , String lab,int size){
        JButton button = new JButton(lab);
        button.setBounds(x,y,width,heigth);
        button.setForeground(new Color(255, 255, 255));
        button.setBorder(new RoundBorder(10));
        button.setOpaque(true);
        button.setBackground(new Color(0, 117, 0));
        button.setFont( new Font(Font.SERIF, Font.PLAIN,  size));
        return button;
    }
    public JLabel constructLabel(int x , int y, int width, int heigth , String lab,int size){
        JLabel label = new JLabel(lab,SwingConstants.CENTER);
        label.setBounds(x,y,width,heigth);
        label.setForeground(Color.GRAY);
        label.setFont( new Font(Font.SERIF, Font.PLAIN,  size));
        return label;
    }
    public JTextField construct_TextField(int x , int y, int width, int heigth , String lab,int size){
        JTextField textField = new JTextField(lab,SwingConstants.CENTER);
        textField.setBounds(x,y,width,heigth);
        textField.setForeground(Color.GRAY);
        textField.setFont( new Font(Font.SERIF, Font.PLAIN,  size));
        return textField;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D graphics2 = (Graphics2D) g;
        graphics2.drawRoundRect(0, 0, this.getWidth()-1, this.getHeight()-1, 20, 20);
    }

    public void changeMessage(String message){
        if(message == null)return;
        this.getMessage().setText(message);
        if(message.length()==0)this.getMessage().setVisible(false);
        else this.getMessage().setVisible(true);
    }
}
