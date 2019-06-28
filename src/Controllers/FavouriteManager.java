package Controllers;

import GUI.SongsPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FavouriteManager {

    public static void addFavourite(String selectedSongPath) {
        System.out.println(selectedSongPath);
        try {

            PrintWriter pw = new PrintWriter(new FileWriter(new File("src/Favourite.txt"), true), true);
            Scanner sc = new Scanner(new FileReader(new File("src/Favourite.txt")));
            pw.println(selectedSongPath);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void removeFavourite(String selectedSongPath) {
        System.out.println(selectedSongPath);
        ArrayList<String> songPaths = new ArrayList<>();
        try {
            int count = 0;
            Scanner sc = new Scanner(new FileReader(new File("src/Favourite.txt")));
            while (sc.hasNext()) {
                String temp=sc.nextLine();
                if(!temp.equals(selectedSongPath)){
                    songPaths.add(temp);
                }
                count++;
            }
            //if the selected song was not on the list do nothing
//            if (index == -1) return;

            PrintWriter pw = new PrintWriter(new FileWriter(new File("src/Favourite.txt"), false), true);
            for (int i = 0; i < count-1; i++) {
                    pw.println(songPaths.get(i));
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
