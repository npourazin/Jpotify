package Controllers;

import GUI.DialogBoxChooseName;
import Logic.Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class RanameDialogBoxListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String oldName=SelectedPlaylistListener.getPlaylistName();
        String newName= DialogBoxChooseName.getTextField().getText();

        //In order to avoid adding an existing playlist
        Scanner sc = null;
        try {
            sc = new Scanner(new FileReader(new File("src/PlaylistNames.txt")));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        while (sc.hasNext()){
            if(sc.nextLine().equals(newName)) {
                System.out.println("playlist exists");
                return;
            }
        }

        //delete the old one
        ArrayList<String> allPlaylist=new ArrayList<>();
        int count=0;
        try {
            sc=new Scanner(new FileReader(new File("src/PlaylistNames.txt")));
            while (sc.hasNext()){
                String temp=sc.nextLine();
                if(!temp.equals(oldName)){
                    allPlaylist.add(temp);
                }
                else{
                    allPlaylist.add(newName);
                }
                count++;
            }


        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        try {
            PrintWriter pw=new PrintWriter(new FileWriter(new File("src/PlaylistNames.txt"),false));

            for (int i = 0; i <count ; i++) {
                pw.println(allPlaylist.get(i));
                pw.flush();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try {
            sc=new Scanner(new FileReader(new File("src/"+oldName+".txt")));
            PrintWriter pw=new PrintWriter(new FileWriter(new File("src/"+newName+".txt")),true);
            while (sc.hasNext()){
                pw.println(sc.nextLine());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        //refresh
        Main.getJpotifyGUI().getChoicesArea().getModel().removeElement(oldName);
        Main.getJpotifyGUI().getChoicesArea().getModel().addElement(newName);

    }
}
