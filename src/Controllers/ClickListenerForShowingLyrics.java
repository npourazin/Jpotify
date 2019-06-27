package Controllers;

import GUI.PlaylistPanel;
import Logic.Main;
import Network.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ClickListenerForShowingLyrics implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String lyrics = "";
        try {
            lyrics = ParseFromweb.parser(ParseFromweb.makeURL(Main.getCurrentQueue().get(Main.getSongQueueIndex()).getAbsolutePath()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String newLyric = "";
        int counter=60;
        for (int i = 0; i < lyrics.length();) {
            if(counter>lyrics.length()) break;
            while (lyrics.charAt(counter)!=' ') {
                counter--;
            }
            newLyric = newLyric+ "\n"+ lyrics.substring(i, counter);
            i=counter;
            counter= i+60;
        }

        //TODO: fix the slider
        JDialog jDialog = new JDialog();
        jDialog.setSize(500, 400);
        jDialog.setVisible(true);

        JTextArea currentSongText = new JTextArea();
        currentSongText.setVisible(true);
        currentSongText.setFont(new Font("Verdana", 9, 13));
        currentSongText.setBackground(Color.cyan);
        currentSongText.setText(newLyric);
        currentSongText.setBorder(new EmptyBorder(30, 20, 30, 20));
        JPanel tmp = new JPanel();
        tmp.setVisible(true);
        tmp.add(currentSongText);

        JScrollPane jScrollPane;
        jScrollPane = new JScrollPane(tmp);
        jScrollPane.setViewportView(currentSongText);
        jScrollPane.setVisible(true);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setVisible(true);

        jDialog.add(jScrollPane);


    }
}






// String newLyric = "";
//        for (int i = 0; i < lyrics.length(); i++) {
//            if (lyrics.charAt(i) == ' ' && i % 10 == 0) {
//                newLyric += lyrics.charAt(i) + "\n";
//            } else {
//                newLyric += lyrics.charAt(i);
//            }
//        }
