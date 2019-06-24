package Controllers;

import GUI.*;
import Logic.Main;
import Logic.PlayerManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClickListenerForShowingSongsList implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        SongsPanel songsPanel = new SongsPanel(PlayerManager.getSongDataArrayList());
        songsPanel.setVisible(true);
        Main.getJpotifyGUI().getHomePanel().setVisible(false);
        Main.getJpotifyGUI().add(songsPanel);
        JLayer<Component> jLayer;
        jLayer = new JLayer<>(songsPanel, new MyLayerUI());
        Main.getJpotifyGUI().add(jLayer, BorderLayout.CENTER);
//        Main.getJpotifyGUI().getContentPane().validate();
        Main.getJpotifyGUI().revalidate();
//        Main.getJpotifyGUI().getContentPane().repaint();
    }

}
