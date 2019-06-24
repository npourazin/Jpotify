package Logic;

import GUI.*;
import Logic.PlayerManager;

import javax.swing.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    private static JpotifyGUI jpotifyGUI;
    private static ObjectOutputStream objOut;
    private static ObjectInputStream objIn;
    private static ArrayList<SongData> currentQueue;
    private static int songQueueIndex = 0;
    private static String myName;
    private static int holyFlag=0;

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
        PlayerManager player = new PlayerManager();

        SignInOrSignUp signInOrSignUp = new SignInOrSignUp();
        while (holyFlag!=1) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        signInOrSignUp.dispose();

        jpotifyGUI=new JpotifyGUI();

        //TODO: write the changes in time and whatever to the file before closing

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

    public static String getMyName() {
        return myName;
    }

    public static void setMyName(String myName) {
        Main.myName = myName;
    }

    public static void setHolyFlag(int holyFlag) {
        Main.holyFlag = holyFlag;
    }
}

///ailee - i will show you my-free-mp3s.com .mp3