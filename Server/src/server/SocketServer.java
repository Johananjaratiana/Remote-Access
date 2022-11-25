package server;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SocketServer {

    int SOCKET_PORT;      // 1522
    String FILE_TO_SEND;  // C:\Users\ITU\Documents\Naina\FileTransfert\Server\ToSend\Ty le izy.txt

    public void setSOCKET_PORT(int SOCKET_PORT) {this.SOCKET_PORT = SOCKET_PORT;}
    public void setFILE_TO_SEND(String FILE_TO_SEND) {this.FILE_TO_SEND = FILE_TO_SEND;}

    public int getSOCKET_PORT() {return SOCKET_PORT;}
    public String getFILE_TO_SEND() {return FILE_TO_SEND;}

    public void main (int port) throws IOException {
        this.setSOCKET_PORT(port);
        FileInputStream fileInputStream = null;
        BufferedInputStream bufferedInputStream = null;
        OutputStream outputStream = null;
        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
            serverSocket = new ServerSocket(this.getSOCKET_PORT());
            while (true) {
                System.out.println("Waiting...");
                try {
                    socket = serverSocket.accept();                         // attend une connectée
                    System.out.println("Accepted connection : " + socket);  // information sur le socket connecté
                    // send file
                    Scanner scanner = new Scanner(System.in);
                    this.setFILE_TO_SEND(scanner.nextLine());               // lien du fichier à envoyer
                    File myFile = new File (this.getFILE_TO_SEND());        // construction du fichier
                    byte [] mybytearray  = new byte [(int)myFile.length()]; //
//                    System.out.println("My file length : "+myFile.length()+" octet(s)");
//                    System.out.println("File to byte : "+mybytearray);
//                    System.out.println(mybytearray.length+" = "+myFile.length());
                    fileInputStream = new FileInputStream(myFile);
                    bufferedInputStream = new BufferedInputStream(fileInputStream);
                    bufferedInputStream.read(mybytearray,0,mybytearray.length);
//                    os = sock.getOutputStream();
//                    System.out.println("Sending " + this.getFILE_TO_SEND() + "\n"+
//                            "\t== > " + mybytearray.length + " bytes");
//                    os.write(mybytearray,0,mybytearray.length);
//                    os.flush();
//                    System.out.println("Done.");
                }
                finally {
//                    if (bis != null) bis.close();
//                    if (os != null) os.close();
//                    if (sock!=null) sock.close();
                }
            }
        }
        finally {
            if (serverSocket != null) serverSocket.close();
        }
    }
}