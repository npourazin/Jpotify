package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class ChoicesArea extends JPanel{
    File file;
    ObjectOutputStream objectOutputStream;
    ChoicesArea(){
        this.setLayout(new FlowLayout());
        JButton saveButton = new JButton("Add Song");
        saveButton.setVisible(true);
        this.add(saveButton);
        saveButton.addActionListener(new clickListenerForAddingSongs());

        JButton songsButton = new JButton("Songs");
        songsButton.setVisible(true);
        this.add(songsButton);
    }
    public class clickListenerForAddingSongs implements ActionListener{
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
}


