package Logic;

import GUI.*;
import Logic.PlayerManager;

import javax.swing.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Main {
    private static JpotifyGUI jpotifyGUI;
    private static ObjectOutputStream objOut;
    private static ObjectInputStream objIn;
    private static String myName;
    private static int holyFlag=0;

    public static void main(String[] args) {

        System.out.println("hello");
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
    public static JpotifyGUI getJpotifyGUI(){
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

    public static void prepareObjOut(){
        try {
            if(objIn!=null){
                objIn.close();
            }
            if(objOut==null)
                objOut = new ObjectOutputStream(new FileOutputStream("src/AddedSongs.bin"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //TODO ? add synchronized?
    public static void prepareObjIn(){
        try {
            if(!new File("src/AddedSongs.bin").exists()) return;
            if(objOut!=null){
                objOut.flush();
                objOut.close();
            }
            if(objIn!=null) {
                objIn.close();
                objIn  = new ObjectInputStream(new FileInputStream("src/AddedSongs.bin"));
            }else {
                objIn  = new ObjectInputStream(new FileInputStream("src/AddedSongs.bin"));
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

//    FileInputStream file = null;
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

//        System.out.println(new SimpleDateFormat("YYYY-MM-dd-HH:mm:ss").format(new Date(0)));
