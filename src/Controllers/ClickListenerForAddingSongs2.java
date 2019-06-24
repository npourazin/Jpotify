package Controllers;

import Logic.*;
import GUI.FileChooser;
//import Logic.AppendingObjectOutputStream;
import javazoom.jl.player.Player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static Logic.SavedSongData.*;

public class ClickListenerForAddingSongs2 implements ActionListener {
    private String absolutePath;
    private File file;

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        file = fileChooser.getSelectedFile();

        if (file==null) return;

        absolutePath  = file.getAbsolutePath();


        if(!absolutePath.endsWith(".mp3")) return;

        try {
            PrintWriter fr = new PrintWriter(new FileWriter(new File("src/AddedSongAdresses.txt"), true));
            Scanner sc = new Scanner(new FileReader(new File("src/AddedSongAdresses.txt")));


            while(sc.hasNext()){
                if(absolutePath.equals(sc.nextLine())){
                    return;
                }
            }

            fr.println(absolutePath);
            fr.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Main.prepareObjOut();
        SavedSongData.addToFile(Main.getObjOut(), absolutePath);
        try {
            Main.getObjOut().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
