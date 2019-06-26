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
    private static String myName = "admin";
    private static int holyFlag=0;

    public static void main(String[] args) {

        currentQueue=null;
        System.out.println("hello");

        SignInOrSignUp signInOrSignUp = new SignInOrSignUp();
        while (holyFlag!=1) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        signInOrSignUp.dispose();

        new PlayerManager();
        PlayerManager.playerManager();

        jpotifyGUI = new JpotifyGUI();

        //TODO: write the changes in time and whatever to the file before closing

    }

    public static void creatCurrentQueueByTime(String fileName) {

        PlayerManager.getSongDataArrayList().sort(new SortByTime());
        //TODO: sort the main array list
        currentQueue=null;
        currentQueue=new ArrayList<SongData>();
        try {
            Scanner sc = new Scanner(new FileReader(new File("src/"+fileName+".txt")));
            //this way we get the current play list in the order that it is stored in the .bin file
            //first we get all the elements from text file
            ArrayList<String> songPaths=new ArrayList<>();
            while (sc.hasNext()){
                songPaths.add(sc.nextLine());
            }

//            System.out.println("main:"+PlayerManager.getSongDataArrayList().size());
//            System.out.println("read:"+songPaths.size());
            for (int i = 0; i <PlayerManager.getSongDataArrayList().size() ; i++) {
                for (int j = 0; j <songPaths.size() ; j++) {
                    if(PlayerManager.getSongDataArrayList().get(i).getAbsolutePath().equals(songPaths.get(j))){
                        currentQueue.add(PlayerManager.getSongDataArrayList().get(i));
                    }
                }
            }

            System.out.println("sub:"+currentQueue.size());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void creatCurrentQueueByRandom(String fileName) {

        PlayerManager.getSongDataArrayList().sort(new SortRandomly());
        //TODO: sort the main array list
        currentQueue=null;
        currentQueue=new ArrayList<SongData>();
        try {
            Scanner sc = new Scanner(new FileReader(new File("src/"+fileName+".txt")));
            //this way we get the current play list in the order that it is stored in the .bin file
            //first we get all the elements from text file
            ArrayList<String> songPaths=new ArrayList<>();
            while (sc.hasNext()){
                songPaths.add(sc.nextLine());
            }

//            System.out.println("main:"+PlayerManager.getSongDataArrayList().size());
//            System.out.println("read:"+songPaths.size());
            for (int i = 0; i <PlayerManager.getSongDataArrayList().size() ; i++) {
                for (int j = 0; j <songPaths.size() ; j++) {
                    if(PlayerManager.getSongDataArrayList().get(i).getAbsolutePath().equals(songPaths.get(j))){
                        currentQueue.add(PlayerManager.getSongDataArrayList().get(i));
                    }
                }
            }

            System.out.println("sub:"+currentQueue.size());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void creatCurrentQueueByAdd(String fileName) {
        currentQueue=null;
        currentQueue=new ArrayList<SongData>();
        try {
            Scanner sc = new Scanner(new FileReader(new File("src/"+fileName+".txt")));
            //this way we get the current play list in the order that it is stored in the .bin file
            //first we get all the elements from text file
            ArrayList<String> songPaths=new ArrayList<>();
            while (sc.hasNext()){
                songPaths.add(sc.nextLine());
            }

                for (int j = 0; j <songPaths.size() ; j++) {
                    for (int i = 0; i <PlayerManager.getSongDataArrayList().size() ; i++) {
                    if(PlayerManager.getSongDataArrayList().get(i).getAbsolutePath().equals(songPaths.get(j))){
                        currentQueue.add(PlayerManager.getSongDataArrayList().get(i));
                    }
                }
            }

            System.out.println("sub:"+currentQueue.size());
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

//💟💟💟❤️
////        prepareObjIn();
//
////        try {
////        try {
////            PrintWriter fr = new PrintWriter(new FileWriter(new File("src/AddedSongAdresses.txt"), true));
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////            //false it to empty file
////            fr.close();
////
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////      new Thread(JpotifyGUI::new).start();