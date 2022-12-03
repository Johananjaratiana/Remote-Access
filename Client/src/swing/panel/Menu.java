package swing.panel;

import swing.listener.ListenerConnect;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Menu extends JPanel {
    public static int menu_width;
    public static int menu_heigth;
    JTextArea adresse_IP;                                // adresse _ IP
    JTextArea port;                                      // port
    ListenerConnect listener_c;                          // Listener Connect ==>  instance of an ClientSocket

    public void setAdresse_IP(JTextArea adresse_IP) {this.adresse_IP = adresse_IP;}
    public void setPort(JTextArea port) {this.port = port;}
    public void setListener_c(ListenerConnect listener_c) {this.listener_c = listener_c;}

    public String getAdresse_IP() {return adresse_IP.getText();}
    public int getPort() throws Exception {
        try {
            return Integer.parseInt(port.getText());
        }catch (Exception e){
            throw new Exception("Port invalid");
        }
    }
    public ListenerConnect getListener_c() {return listener_c;}

    public Menu(int width, int height) throws IOException {
        this.menu_width = width/2;
        this.menu_heigth = (height/30)*14;

        int font_size = 30;
        int margin_v = height/30;

        this.addLabel(0,margin_v,width/2,margin_v,"IP_ADRESS");       // etiquette IP
        this.setAdresse_IP(new JTextArea());
        this.adresse_IP.setLocation(menu_width/4,margin_v*3);
        this.adresse_IP.setSize(menu_width/2,margin_v);
        this.adresse_IP.setFont(this.getFont_title(font_size));
        this.adresse_IP.append("192.168.88.12");

        this.addLabel(0,margin_v*5,width/2,margin_v,"PORT");      // etiquette Port
        this.setPort(new JTextArea());
        this.port.setLocation(menu_width/4,margin_v*7);
        this.port.setSize(menu_width/2,margin_v);
        this.port.setFont(this.getFont_title(font_size));
        this.port.append("1522");

        JButton connect = new JButton("Connect...");
        connect.setLocation(menu_width*5/12,margin_v*10);
        connect.setSize(menu_width/6,margin_v*3/4);
        connect.setFont(this.getFont_title(font_size/2));
        connect.setBorder(new RoundBorder(10));
        this.setListener_c(new ListenerConnect(this));
        connect.addMouseListener(this.getListener_c());
        this.add(connect);

        this.setBackground(new Color(218, 218, 218, 137));

        this.add(this.adresse_IP,1);                                            // IP insert
        this.add(this.port,1);                                                  // Port insert
        this.setLocation(this.menu_width/2, margin_v-(margin_v/2));
        this.setSize(this.menu_width,this.menu_heigth);
        this.setLayout(null);
        this.setVisible(true);
    }

    public void addLabel(int x , int y, int width, int heigth , String lab){
        JLabel label = new JLabel(lab,SwingConstants.CENTER);
        label.setBounds(x,y,width,heigth);
        label.setForeground(Color.GRAY);
        label.setFont( new Font(Font.SERIF, Font.PLAIN,  50));
        this.add(label);
    }
    public Font getFont_title(int size){
        return new Font(Font.SERIF, Font.PLAIN,  size);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D graphics2 = (Graphics2D) g;
        graphics2.drawRoundRect(0, 0, menu_width-1, menu_heigth-1, 20, 20);
    }
}
