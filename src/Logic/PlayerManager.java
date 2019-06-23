package Logic;

import Controllers.ClickListenerForAddingSongs;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;

import java.io.*;
import java.time.Instant;
import java.util.*;

public class PlayerManager {
    private static SongPlayer sP;
    private static ArrayList<SongData> songDataArrayList;
    private ArrayList<String> addresses;
    private ObjectInputStream objectInputStream;

    public PlayerManager() {
        Scanner sc;

        File file;

            addresses = new ArrayList<>();
            songDataArrayList = new ArrayList<>();
//            objectInputStream = new ObjectInputStream(new FileInputStream("src/AddedSongs.bin"));

            readSongDataFromFile();
            //create a button for each on song panel
            //by validating songs panel?


            try {
                sP = new SongPlayer("/AUT/Term2/JpotifyFinalProject/songs/DeanLewis.mp3");
                //Mahvash:  :/AUT/Term2/JpotifyFinalProject/songs/DeanLewis.mp3
                //Niki: /home/niki/Desktop/ailee - i will show you my-free-mp3s.com .mp3
            } catch (JavaLayerException e) {
                e.printStackTrace();
            }

    }

    public static SongPlayer getsP() {
        return sP;
    }

    public void readSongDataFromFile() {

        try {
            int flag=0;
            SongData tmp;
            if(ClickListenerForAddingSongs.getObjectInputStream()!=null) flag=1;
            else {
                objectInputStream = new ObjectInputStream(new FileInputStream("src/AddedSongs.bin"));
            }

            while (true) {
                if(flag==1)
                    tmp = (SongData) ClickListenerForAddingSongs.getObjectInputStream().readObject();
                else {
                    tmp = (SongData) objectInputStream.readObject();
                }
                songDataArrayList.add(tmp);

            }
        } catch (EOFException e) { }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        songDataArrayList.sort(new SortByTime());

    }

    public static ArrayList<SongData> getSongDataArrayList() {
        return songDataArrayList;
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