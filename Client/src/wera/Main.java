package wera;

import swing.frame.Fenetre;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        try {
            Fenetre fenetre = new Fenetre();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}