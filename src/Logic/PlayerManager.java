package Logic;

import Controllers.PauseAndPlayClickButton;
import javazoom.jl.decoder.JavaLayerException;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class PlayerManager {
    private static SongPlayer sP;
    private static ArrayList<SongData> songDataArrayList;
    private ArrayList<String> addresses;
    private ObjectInputStream objectInputStream;

    public static void PlayerManager() {
//        Scanner sc;
//        File file;

//          addresses = new ArrayList<>();
            songDataArrayList = new ArrayList<>();
//          objectInputStream = new ObjectInputStream(new FileInputStream("src/AddedSongs.bin"));

            Main.prepareObjIn();
            SavedSongData.readFromFile(Main.getObjIn());
            songDataArrayList.sort(new SortByTime());

            Main.creatCurrentQueue("src/AddedSongAdresses.txt");
            //create a button for each on song panel
            //by validating songs panel?


        try {
            if(Main.getCurrentQueue().size()==0) return;
            System.out.println("Queue size: "+Main.getCurrentQueue().size());
            System.out.println(Main.getCurrentQueue().get(Main.getSongQueueIndex()).getAbsolutePath());
            sP = new SongPlayer(Main.getCurrentQueue().get(Main.getSongQueueIndex()).getAbsolutePath());
            PauseAndPlayClickButton.setIfNewSong();

//            Mahvash:  :/AUT/Term2/JpotifyFinalProject/songs/DeanLewis.mp3
//            Niki: /home/niki/Desktop/ailee - i will show you my-free-mp3s.com .mp3
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
    }

    public static SongPlayer getsP() {
        return sP;
    }


    public static ArrayList<SongData> getSongDataArrayList() {
        return songDataArrayList;
    }

    public static void setSongDataArrayList(ArrayList<SongData> songDataArrayList) {
        PlayerManager.songDataArrayList = songDataArrayList;
    }
}


class SortByTime implements Comparator<SongData> {

    @Override
    public int compare(SongData songData, SongData t1) {
        if (songData.getLastTimeListened() != t1.getLastTimeListened()) {
            if (songData.getLastTimeListened().after(t1.getLastTimeListened())) {
                return 1;
            } else return -1;
        }
        if (songData.getTimeItWasAdded().after(t1.getTimeItWasAdded())) {
            return 1;
        } else if (songData.getTimeItWasAdded().before(t1.getTimeItWasAdded())) {
            return -1;
        }

        return 0;
    }
}