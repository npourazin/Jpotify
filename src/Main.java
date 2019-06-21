import GUI.*;
import Logic.Music;
import Logic.Player;
import Logic.SongData;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import Logic.Song;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        System.out.println("hello");
        Player player = new Player();
//        try {
//            PrintWriter fr = new PrintWriter(new FileWriter(new File("src/AddedSongAdresses.txt"), true));
//            //false it to empty file
//            fr.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
       new Thread(JpotifyGUI::new).start();

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


    }
}

///ailee - i will show you my-free-mp3s.com .mp3