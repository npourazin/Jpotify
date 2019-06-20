import GUI.*;
import Logic.Music;
import Logic.SongData;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
//        System.out.println("hello");
//        try {
//            PrintWriter fr = new PrintWriter(new FileWriter(new File("src/AddedSongAdresses.txt"), true));
//            //false it to empty file
//            fr.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        new JpotifyGUI();

        new Thread(() -> {
            try {
                SongData onDesk = new SongData("/home/niki/Desktop/Eir Aoi - INNOCENCE .mp3", new Date(0));
                new Music(onDesk).playSong();
            } catch (IOException | InvalidDataException | UnsupportedTagException e) {
                e.printStackTrace();
            }
        }).start();

        System.out.println(new SimpleDateFormat("YYYY-MM-dd-HH:mm:ss").format(new Date(0)));


    }
}

///ailee - i will show you my-free-mp3s.com .mp3