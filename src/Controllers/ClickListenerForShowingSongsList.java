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
//        SongsPanel songsPanel = new SongsPanel(PlayerManager.getSongDataArrayList());
        Main.getJpotifyGUI().remove(Main.getJpotifyGUI().getAlbumsPanel());
        Main.getJpotifyGUI().remove(Main.getJpotifyGUI().getHomePanel());

        Main.getJpotifyGUI().getSongsPanel().setVisible(true);
        Main.getJpotifyGUI().getHomePanel().setVisible(false);
        Main.getJpotifyGUI().getAlbumsPanel().setVisible(false);
        Main.getJpotifyGUI().add(Main.getJpotifyGUI().getSongsPanel().getjScrollPane(), BorderLayout.CENTER);


        JLayer<Component> jLayer;
        jLayer = new JLayer<>(Main.getJpotifyGUI().getSongsPanel(), new MyLayerUI());
        Main.getJpotifyGUI().add(jLayer, BorderLayout.CENTER);
        Main.getJpotifyGUI().validate();
        Main.getJpotifyGUI().revalidate();
        Main.getJpotifyGUI().repaint();
    }

}
