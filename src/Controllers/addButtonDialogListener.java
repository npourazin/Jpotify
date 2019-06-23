package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class addButtonDialogListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String playlistName=ClickListenerForNewPlayList.getTextField().getText();
        System.out.println(playlistName);

        try {
//            Scanner sc = new Scanner(new FileReader(new File("src/PlaylistNames.txt")));
            PrintWriter fr = new PrintWriter(new FileWriter(new File("src/PlaylistNames.txt"), true));
            fr.println(playlistName);
            fr.flush();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
