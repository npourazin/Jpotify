package Controllers;

import GUI.SongsPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class RemoveFavouriteMenuClicked implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem menuItem=(JMenuItem)(e.getSource());
        System.out.println(SongsPanel.getFavouriteSong());
        ArrayList<String> songPaths=new ArrayList<>();
        try {
            int count=0;
            Scanner sc=new Scanner(new FileReader(new File("src/Favourite.txt")));
            while (sc.hasNext()){
                String temp=sc.nextLine();
                if(!temp.equals(SongsPanel.getFavouriteSong()))
                    songPaths.add(temp);
                    count++;
            }
            PrintWriter pw=new PrintWriter(new FileWriter(new File("src/Favourite.txt"),false),true);
            for (int i = 0; i <count-1 ; i++) {
                pw.println(songPaths.get(i));
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
