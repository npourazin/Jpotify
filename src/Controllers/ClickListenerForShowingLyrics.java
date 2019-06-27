package Controllers;

import GUI.PlaylistPanel;
import Logic.Main;
import Network.*;

import javax.swing.*;
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
        for (int i = 0; i <lyrics.length() ; i++) {
            if(lyrics.charAt(i)==' ' && i%10==0){
                newLyric+=lyrics.charAt(i)+"\n";
            }else {
                newLyric+=lyrics.charAt(i);
            }
        }

        //TODO: fix the slider
        JDialog jDialog = new JDialog();
        jDialog.setSize(400, 400);
        jDialog.setVisible(true);
        JTextArea currentSongText=new JTextArea();
        currentSongText.setVisible(true);
        currentSongText.setFont(new Font("Verdana", 9, 13));
        currentSongText.setBackground(Color.cyan);
        currentSongText.setText(newLyric);
        JScrollPane jScrollPane;
        jScrollPane = new JScrollPane(currentSongText);
        jScrollPane.setViewportView(currentSongText);
        jScrollPane.setVisible(true);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setVisible(true);
        jDialog.add(currentSongText,BorderLayout.CENTER);


    }
}
