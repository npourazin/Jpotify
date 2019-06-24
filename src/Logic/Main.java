package Logic;

import GUI.*;
import Logic.PlayerManager;

import javax.swing.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
    private static JpotifyGUI jpotifyGUI;
    private static ObjectOutputStream objOut;
    private static ObjectInputStream objIn;
    private static ArrayList<SongData> currentQueue;
    private static int songQueueIndex = 0;

    public static void main(String[] args) {

        System.out.println("hello");

//        prepareObjIn();

        PlayerManager.PlayerManager();
//        try {
//        try {
//            PrintWriter fr = new PrintWriter(new FileWriter(new File("src/AddedSongAdresses.txt"), true));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//            //false it to empty file
//            fr.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//      new Thread(JpotifyGUI::new).start();
        jpotifyGUI = new JpotifyGUI();

        //TODO: write the changes in time and whatever to the file before closing


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

    public static void creatCurrentQueue(String fileName) {
        currentQueue=null;
        currentQueue=new ArrayList<SongData>();
        try {
            Scanner sc = new Scanner(new FileReader(new File(fileName)));
            while (sc.hasNext()){
                String absolutePath=sc.nextLine();
                for (int i = 0; i <PlayerManager.getSongDataArrayList().size() ; i++) {
                    if(PlayerManager.getSongDataArrayList().get(i).getAbsolutePath().equals(absolutePath)){
                        currentQueue.add(PlayerManager.getSongDataArrayList().get(i));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }



    public static void setCurrentQueue(ArrayList<SongData> arr) {
        currentQueue = arr;
    }

    public static int getSongQueueIndex() {
        return songQueueIndex;
    }

    public static void setSongQueueIndex(int i) {
        songQueueIndex = i;
    }

    public static ArrayList<SongData> getCurrentQueue() {
        return currentQueue;
    }

    public static JpotifyGUI getJpotifyGUI() {
        return jpotifyGUI;
    }

    public static ObjectOutputStream getObjOut() {
        return objOut;
    }

    public static void setObjOut(ObjectOutputStream objOut) {
        Main.objOut = objOut;
    }

    public static ObjectInputStream getObjIn() {
        return objIn;
    }

    public static void setObjIn(ObjectInputStream objIn) {
        Main.objIn = objIn;
    }

    public static void prepareObjOut() {
        try {
            if (objIn != null) {
                objIn.close();
            }
            if (objOut == null)
                objOut = new ObjectOutputStream(new FileOutputStream("src/AddedSongs.bin"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //TODO ? add synchronized?
    public static void prepareObjIn() {
        try {
            if (!new File("src/AddedSongs.bin").exists()) return;
            if (objOut != null) {
                objOut.flush();
                objOut.close();
            }
            if (objIn != null) {
                objIn.close();
                objIn = new ObjectInputStream(new FileInputStream("src/AddedSongs.bin"));
            } else {
                objIn = new ObjectInputStream(new FileInputStream("src/AddedSongs.bin"));
            }

        } catch (IOException e) {
            //Todo figure out this damned exception
            e.printStackTrace();
        }

    }
}

///ailee - i will show you my-free-mp3s.com .mp3