package Controllers;

import GUI.AlbumsPanel;
import Logic.Main;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClickListenerForShowingAlbums implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        //cleaning everything
        Main.getJpotifyGUI().getHomePanel().setVisible(true);
        Main.getJpotifyGUI().getHomePanel().removeAll();
        Main.getJpotifyGUI().getContentPane().invalidate();
        Main.getJpotifyGUI().getContentPane().revalidate();
        Main.getJpotifyGUI().getContentPane().repaint();

        //making new panel show up
        AlbumsPanel albumsPanel=new AlbumsPanel();
        Main.getJpotifyGUI().setAlbumsPanel(albumsPanel);
        Main.getJpotifyGUI().getHomePanel().removeAll();
        Main.getJpotifyGUI().getHomePanel().add(Main.getJpotifyGUI().getAlbumsPanel(),BorderLayout.CENTER);
        Main.getJpotifyGUI().revalidate();
    }
}
