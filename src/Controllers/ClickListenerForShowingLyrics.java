package Controllers;

import Logic.Main;
import Network.*;

import javax.swing.*;
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
        JFrame jDialog = new JFrame();
        jDialog.setSize(400, 400);
        jDialog.setVisible(true);
        JLabel currentSongLable = new JLabel(lyrics);
        currentSongLable.setVisible(true);
        currentSongLable.setFont(new Font("Verdana", 9, 13));
        currentSongLable.setBackground(Color.cyan);
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        jDialog.add(currentSongLable, BorderLayout.CENTER);
    }
}
