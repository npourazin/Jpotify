package Controllers;

import GUI.DialogBoxChooseName;
import GUI.MyLayerUI;
import Logic.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.*;
import java.util.Scanner;

public class ClickListenerForNewPlayList implements ActionListener {
    private static boolean visible=false;
    @Override
    public void actionPerformed(ActionEvent e) {
        visible=true;
        new DialogBoxChooseName();

        try {
            Scanner sc = new Scanner(new FileReader(new File("src/PlaylistNames.txt")));
            PrintWriter fr = new PrintWriter(new FileWriter(new File("src/PlaylistNames.txt"), true));

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }



    }

}
