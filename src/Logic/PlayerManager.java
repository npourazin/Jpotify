package Logic;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;

import java.io.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class PlayerManager {
    private static SongPlayer sP;

    public PlayerManager() {
        ObjectInputStream objectInputStream;
        Scanner sc;
        ArrayList<SongData> songDataArrayList;
        ArrayList<String> addresses;
        File file;
        try {
            addresses = new ArrayList<>();
            songDataArrayList = new ArrayList<>();
            objectInputStream  = new ObjectInputStream(new FileInputStream("src/AddedSongs.bin"));
//            sc = new Scanner(new FileReader(new File("src/AddedSongAdresses.txt")));
//            while(sc.hasNext()){
//                String tmp = sc.nextLine();
//                addresses.add(tmp);
//            }

            try {
                SongData tmp;
                while (true) {
                    tmp = (SongData) objectInputStream.readObject();
                    songDataArrayList.add(tmp);
                }
            }catch (EOFException e){}

            //sort song datas by creation time and listened time

            //create a button for each on song panel
            //by validating songs panel?

//            PrintWriter fr = new PrintWriter(new FileWriter(new File("src/AddedSongAdresses.txt"), true));


//            String absolutePath  = file.getAbsolutePath();
//
//            while(sc.hasNext()){
//                if(absolutePath.equals(sc.nextLine())){
//                    return;
//                }
//            }
//
//            SongData songData = new SongData(absolutePath, Date.from(Instant.now()));
//            objectOutputStream.writeObject(songData);
//
//            //it only reads mp3 files
//            //Space for R&D!!
//            if(!absolutePath.endsWith(".mp3")) return;
//            fr.println(absolutePath);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        try {
            sP = new SongPlayer("/home/niki/Desktop/ailee - i will show you my-free-mp3s.com .mp3");
            //Mahvash:  :/AUT/Term2/JpotifyFinalProject/songs/DeanLewis.mp3
            //Niki: /home/niki/Desktop/ailee - i will show you my-free-mp3s.com .mp3
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
    }

    public static SongPlayer getsP() {
        return sP;
    }
}
