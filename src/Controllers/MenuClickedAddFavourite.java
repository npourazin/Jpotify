package Controllers;

import GUI.SongsPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class MenuClickedAddFavourite implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem menuItem=(JMenuItem)(e.getSource());
        System.out.println(SongsPanel.getFavouriteSong());
        try {

            PrintWriter pw=new PrintWriter(new FileWriter(new File("src/Favourite.txt"),true),true);
            Scanner sc=new Scanner(new FileReader(new File("src/Favourite.txt")));
            while (sc.hasNext()){
                if(sc.nextLine().equals(SongsPanel.getFavouriteSong()))
                    return;
            }
            pw.println(SongsPanel.getFavouriteSong());

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
