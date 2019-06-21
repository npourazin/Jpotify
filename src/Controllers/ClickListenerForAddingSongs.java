package Controllers;
import GUI.FileChooser;
import Logic.SongData;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.Instant;
import java.util.Date;
import java.util.Scanner;

public class ClickListenerForAddingSongs implements ActionListener {
    private File file;
    private ObjectOutputStream objectOutputStream;
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        file = fileChooser.getSelectedFile();
        if(file==null){
            return;
        }
        try {
            objectOutputStream  = new ObjectOutputStream(new FileOutputStream("src/AddedSongs.bin"));
            FileInputStream in = new FileInputStream(file.getAbsolutePath());
            Scanner sc = new Scanner(new FileReader(new File("src/AddedSongAdresses.txt")));
            PrintWriter fr = new PrintWriter(new FileWriter(new File("src/AddedSongAdresses.txt"), true));

            String absolutePath  = file.getAbsolutePath();

            while(sc.hasNext()){
                if(absolutePath.equals(sc.nextLine())){
                    return;
                }
            }

            SongData songData = new SongData(absolutePath, Date.from(Instant.now()));
            objectOutputStream.writeObject(songData);

            //it only reads mp3 files
            //Space for R&D!!
            if(!absolutePath.endsWith(".mp3")) return;
            fr.println(absolutePath);
            fr.flush();

        } catch (IOException | InvalidDataException | UnsupportedTagException e) {
            e.printStackTrace();
        }

    }
}