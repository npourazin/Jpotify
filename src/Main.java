import GUI.*;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.DirectoryIteratorException;

public class Main {

    public static void main(String[] args) {
        System.out.println("hello");
        try {
            PrintWriter fr = new PrintWriter(new FileWriter(new File("src/AddedSongAdresses.txt"), true));
            //false it to empty file
            fr.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        new JpotifyGUI();



    }
}
