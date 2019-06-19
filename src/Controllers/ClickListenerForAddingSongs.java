package Controllers;
import GUI.*;
import GUI.FileChooser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Formatter;
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
            Scanner sc = new Scanner(new FileReader(file));
            PrintWriter fr = new PrintWriter(new FileWriter(new File("src/AddedSongAdresses.txt"), true));
            String absolutePath  = file.getAbsolutePath();
            //it only reads mp3 files
            //Space for R&D!!
            if(!absolutePath.endsWith(".mp3")) return;
//            int c;
//            while ((c = in.read()) != -1) {
//                objectOutputStream.write(c);
//            }
            fr.println(absolutePath);
            fr.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}