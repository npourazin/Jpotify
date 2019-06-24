package Controllers;

import GUI.AlbumsPanel;
import GUI.MyLayerUI;
import GUI.SongsPanel;
import Logic.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClickListenerForShowingAlbums implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        AlbumsPanel albumsPanel=new AlbumsPanel();
        albumsPanel.setVisible(true);
        Main.getJpotifyGUI().getHomePanel().removeAll();
        Main.getJpotifyGUI().getHomePanel().add(albumsPanel);
        JLayer<Component> jLayer;
        jLayer = new JLayer<>(albumsPanel, new MyLayerUI());
        Main.getJpotifyGUI().add(jLayer, BorderLayout.CENTER);
//        Main.getJpotifyGUI().validate();
        Main.getJpotifyGUI().revalidate();
//        Main.getJpotifyGUI().repaint();
    }
}
