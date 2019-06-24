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
//        AlbumsPanel albumsPanel=new AlbumsPanel();
        Main.getJpotifyGUI().remove(Main.getJpotifyGUI().getSongsPanel());
        Main.getJpotifyGUI().remove(Main.getJpotifyGUI().getHomePanel());

//        Main.getJpotifyGUI().getAlbumsPanel().setVisible(true);
//        Main.getJpotifyGUI().getHomePanel().setVisible(false);
//        Main.getJpotifyGUI().getSongsPanel().setVisible(false);
        Main.getJpotifyGUI().add(Main.getJpotifyGUI().getAlbumsPanel().getjScrollPane(), BorderLayout.CENTER);


        JLayer<Component> jLayer;
        jLayer = new JLayer<>(Main.getJpotifyGUI().getAlbumsPanel(), new MyLayerUI());
        Main.getJpotifyGUI().add(jLayer, BorderLayout.CENTER);
        Main.getJpotifyGUI().validate();
        Main.getJpotifyGUI().revalidate();
        Main.getJpotifyGUI().repaint();
    }
}
