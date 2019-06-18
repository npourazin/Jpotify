package Controllers;
import GUI.*;
import GUI.FileChooser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

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
            int c;
            while ((c = in.read()) != -1) {
                objectOutputStream.write(c);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}