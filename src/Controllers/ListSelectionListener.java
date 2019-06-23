package Controllers;

import GUI.FileChooser;

import javax.swing.event.ListSelectionEvent;
import java.io.*;
import java.util.Scanner;

public class ListSelectionListener implements javax.swing.event.ListSelectionListener {
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {
            int index=e.getFirstIndex();
            String playlistName=null;
            System.out.println(e.getFirstIndex());
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.getSelectedFile();
            if(file==null){
                return;
            }
            try {
                //TODO: overwrite ALLERT!! fix this
                Scanner sc = new Scanner(new FileReader(new File("src/playlistNames.txt")));
                for (int i = 0; i <=index ; i++) {
                    playlistName=sc.nextLine();
                }
                System.out.println("src/"+playlistName);
                PrintWriter playlistFR = new PrintWriter(new FileWriter(new File("src/"+playlistName+".txt"), true));
                playlistFR.println(file.getAbsolutePath());
                playlistFR.flush();
                PrintWriter songsFR = new PrintWriter(new FileWriter(new File("src/AddedSongAdresses.txt"), true));
                songsFR.println(file.getAbsolutePath());
                songsFR.flush();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }


    }
}

