package swing.panel;

import swing.frame.Fenetre;
import swing.listener.Listener_add_connect;
import swing.listener.Listener_start_stop;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Vector;

public class Menu extends JPanel {
    public static int menu_width;
    public static int menu_heigth;
    JTextArea adresse_IP;                                // adresse _ IP
    JTextArea port;                                      // port
    JTextArea reconnaisance;                                      // reconnaissance
    Listener_add_connect listener_c;                          // Listener Connect ==>  instance of an ClientSocket
    Vector<Proper_Bouton> bouton_cn_dscn = new Vector<>();
    int n = 0;

    public void setAdresse_IP(JTextArea adresse_IP) {this.adresse_IP = adresse_IP;}

    public void setPort(JTextArea port) {this.port = port;}

    public void setReconnaisance(JTextArea reconnaisance) {this.reconnaisance = reconnaisance;}

    public void setListener_c(Listener_add_connect listener_c) {this.listener_c = listener_c;}

    public String getAdresse_IP() {return adresse_IP.getText();}

    public int getPort() throws Exception {
        try {
            return Integer.parseInt(port.getText());
        }catch (Exception e){
            throw new Exception("Port invalid");
        }
    }

    public String getReconnaisance() {return reconnaisance.getText();}

    public Listener_add_connect getListener_c() {return listener_c;}

    public Vector<Proper_Bouton> getBouton_cn_dscn() {return bouton_cn_dscn;}

    public Menu(int width, int height, Fenetre fenetre) throws IOException {
        this.menu_width = width/2;
        this.menu_heigth = (height/30)*15;

        int font_size = 30;
        int margin_v = height/30;

        this.addLabel(0,margin_v,width/2,margin_v,"IP_ADRESS",50);       // etiquette IP
        this.setAdresse_IP(new JTextArea());
        this.adresse_IP.setLocation(menu_width/4,margin_v*3);
        this.adresse_IP.setSize(menu_width/2,margin_v);
        this.adresse_IP.setFont(this.getFont_title(font_size));
        this.adresse_IP.append("192.168.43.211");

        this.addLabel(0,margin_v*5,width/2,margin_v,"PORT",50);      // etiquette Port
        this.setPort(new JTextArea());
        this.port.setLocation(menu_width/4,margin_v*7);
        this.port.setSize(menu_width/2,margin_v);
        this.port.setFont(this.getFont_title(font_size));
        this.port.append("1522");

        this.addLabel(0,margin_v*9,width/2,margin_v,"RECONNAISSANCE",40);// etiquette Reconaissance
        this.setReconnaisance(new JTextArea());
        this.reconnaisance.setLocation(menu_width/4,margin_v*11);
        this.reconnaisance.setSize(menu_width/2,margin_v);
        this.reconnaisance.setFont(this.getFont_title(font_size));
        this.reconnaisance.append("Port 15..");

        JButton connect = new JButton("Connect...");
        connect.setLocation(menu_width*5/12,margin_v*13);
        connect.setSize(menu_width/6,margin_v*3/4);
        connect.setFont(this.getFont_title(font_size/2));
        connect.setBorder(new RoundBorder(10));
        this.setListener_c(new Listener_add_connect(this,fenetre));
        connect.addMouseListener(this.getListener_c());
        this.add(connect);

        this.setBackground(new Color(218, 218, 218, 137));

        this.add(this.adresse_IP,1);    // IP insert
        this.add(this.port,1);          // Port insert
        this.add(this.reconnaisance,1); // Port insert
        this.setLocation(this.menu_width/2, margin_v/4);
        this.setSize(this.menu_width,this.menu_heigth);
        this.setLayout(null);
        this.setVisible(true);
    }

    public void addLabel(int x , int y, int width, int heigth , String lab,int size){
        JLabel label = new JLabel(lab,SwingConstants.CENTER);
        label.setBounds(x,y,width,heigth);
        label.setForeground(Color.GRAY);
        label.setFont( new Font(Font.SERIF, Font.PLAIN,  size));
        this.add(label);
    }
    public Proper_Bouton constructButton(int x , int y, int width, int heigth , String lab,int size){
        Proper_Bouton label = new Proper_Bouton(lab);
        label.setBounds(x,y,width,heigth);
        label.setForeground(new Color(255, 255, 255));
        label.setBorder(new RoundBorder(10));
        label.setOpaque(true);
        label.setBackground(new Color(0, 117, 0));
        label.setFont( new Font(Font.SERIF, Font.PLAIN,  size));
        return label;
    }

    public void update_connected(){
        int width = this.getWidth()/2;
        int heigth = this.getHeight()/10;
        int n2 = this.getListener_c().getPC().size();
        Proper_Bouton proper_bouton = null;
        String text =  null;
        if(n2 != 0){
            for(int a = n ; a < n2 ; a++){
//                text = this.getListener_c().getPC().get(a).getSERVER()+" || "+this.getListener_c().getPC().get(a).getSOCKET_PORT();
                text = this.getListener_c().getPC().get(a).getReconnaissance();
                proper_bouton = constructButton(width/5,a*(heigth),width*3/5,heigth,text,20);
                proper_bouton.setClientMultipleServer(this.getListener_c().getPC().get(a));
                proper_bouton.addMouseListener(new Listener_start_stop(proper_bouton));
                proper_bouton.setFocusable(false);
                this.bouton_cn_dscn.add(proper_bouton);
            }
            n = n2;
        }
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
