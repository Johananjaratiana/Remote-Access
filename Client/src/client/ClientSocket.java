package client;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientSocket {

    int SOCKET_PORT;      // you may change this                // 1522
    String SERVER;  // localhost                                //  = "127.0.0.1"
    String FILE_TO_RECEIVED;                                    //"c:/temp/source-downloaded.pdf"
    // you may change this, I give a different name because i don't want to
    // overwrite the one used by server...

    int FILE_SIZE = 6022386;                                    // 6022386
    // file size temporary hard coded
    // should bigger than the file to be downloaded


    public void setSOCKET_PORT(int SOCKET_PORT) {this.SOCKET_PORT = SOCKET_PORT;}
    public void setSERVER(String SERVER) {this.SERVER = SERVER;}
    public void setFILE_TO_RECEIVED(String FILE_TO_RECEIVED) {this.FILE_TO_RECEIVED = FILE_TO_RECEIVED;}
    public void setFILE_SIZE(int FILE_SIZE) {this.FILE_SIZE = FILE_SIZE;}

    public int getSOCKET_PORT() {return SOCKET_PORT;}
    public String getSERVER() {return SERVER;}
    public String getFILE_TO_RECEIVED() {return FILE_TO_RECEIVED;}
    public int getFILE_SIZE() {return FILE_SIZE;}

    public void main (String server, int port) throws IOException {
        this.setSERVER(server);
        this.setSOCKET_PORT(port);
        int bytesRead;
        int current = 0;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        Socket sock = null;
        try {
            sock = new Socket(SERVER, SOCKET_PORT);
            System.out.println("Connecting...");

            // receive file
            byte [] mybytearray  = new byte [this.getFILE_SIZE()];
            InputStream is = sock.getInputStream();
            Scanner scanner = new Scanner(System.in);
            this.setFILE_TO_RECEIVED(scanner.nextLine());
            fos = new FileOutputStream(this.getFILE_TO_RECEIVED());
            bos = new BufferedOutputStream(fos);
            bytesRead = is.read(mybytearray,0,mybytearray.length);
            current = bytesRead;

            do {
                bytesRead =
                        is.read(mybytearray, current, (mybytearray.length-current));
                if(bytesRead >= 0) current += bytesRead;
            } while(bytesRead > -1);

            bos.write(mybytearray, 0 , current);
            bos.flush();
            System.out.println("File " + FILE_TO_RECEIVED
                    + " downloaded (" + current + " bytes read)");
        }
        finally {
            if (fos != null) fos.close();
            if (bos != null) bos.close();
            if (sock != null) sock.close();
        }
    }

}