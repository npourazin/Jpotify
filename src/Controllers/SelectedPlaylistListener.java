package Controllers;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class SelectedPlaylistListener implements javax.swing.event.ListSelectionListener {
    private static String playlistName;
    @Override
    public void valueChanged(ListSelectionEvent e) {
//        ListSelectionModel element = (ListSelectionModel)e.getSource();
        if (e.getValueIsAdjusting() == false) {
            int index = e.getFirstIndex();
            playlistName = null;
            System.out.println(e.getFirstIndex());
            Scanner sc = null;
            try {
                sc = new Scanner(new FileReader(new File("src/playlistNames.txt")));
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            for (int i = 0; i <= index; i++) {
                playlistName = sc.nextLine();
            }
        }
    }

    public static String getPlaylistName(){
        return playlistName;
    }
}
