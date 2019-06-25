package Controllers;
import GUI.DialogBoxChooseName;
import Logic.Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class addButtonDialogListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String playlistName= DialogBoxChooseName.getTextField().getText();
        System.out.println(playlistName);

        try {

            PrintWriter fr = new PrintWriter(new FileWriter(new File("src/PlaylistNames.txt"), true));
            //In order to avoid adding an existing playlist
            Scanner sc = new Scanner(new FileReader(new File("src/PlaylistNames.txt")));
            while (sc.hasNext()){
                if(sc.nextLine().equals(playlistName)) {
                    System.out.println("playlist exists");
                    return;
                }
            }
            fr.println(playlistName);
            fr.flush();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        //refresh
        Main.getJpotifyGUI().getChoicesArea().getModel().addElement(playlistName);
        //TODO:Dialog box for choosing songs to add
    }
}
