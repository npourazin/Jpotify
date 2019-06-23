package Logic;

import GUI.*;
import Logic.PlayerManager;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    private static JpotifyGUI jpotifyGUI;
    public static void main(String[] args) {
        System.out.println("hello");
        PlayerManager player = new PlayerManager();
//        try {
        try {
            PrintWriter fr = new PrintWriter(new FileWriter(new File("src/AddedSongAdresses.txt"), true));
        } catch (IOException e) {
            e.printStackTrace();
        }
//            //false it to empty file
//            fr.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//      new Thread(JpotifyGUI::new).start();
        jpotifyGUI=new JpotifyGUI();

//        Thread t = new Thread(() -> {
//            try {
//                SongData onDesk = new SongData("D:/AUT/Term2/JpotifyFinalProject/songs/DeanLewis.mp3", new Date(0));
//                new Music(onDesk).playSong();
//            } catch (IOException | InvalidDataException | UnsupportedTagException e) {
//                e.printStackTrace();
//            }
//        });
//        t.setPriority(Thread.MAX_PRIORITY);
//        t.start();

        System.out.println(new SimpleDateFormat("YYYY-MM-dd-HH:mm:ss").format(new Date(0)));
//        FileInputStream file = null;
//        AdvancedPlayer player = null;
//        int count=0;
//        try {
//            file = new FileInputStream("/AUT/Term2/JpotifyFinalProject/songs/DeanLewis.mp3");
//            player = new AdvancedPlayer(file);
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            System.out.println("Failed to play the file.");
//        } catch (JavaLayerException e) {
//            e.printStackTrace();
//        }
//        while (true) {
//            try {
//                if(!player.decodeFrame()) break;
//                count++;
//            } catch (JavaLayerException e) {
//                e.printStackTrace();
//            }
//        }

    }
    public static JpotifyGUI getJpotifyGUI(){
        return jpotifyGUI;
    }

}

///ailee - i will show you my-free-mp3s.com .mp3