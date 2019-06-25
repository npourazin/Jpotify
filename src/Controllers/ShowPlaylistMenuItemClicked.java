package Controllers;

import GUI.PlaylistPanel;
import GUI.SongsPanel;
import Logic.Main;
import Logic.PlayerManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowPlaylistMenuItemClicked implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println(SelectedPlaylistListener.getPlaylistName());
        System.out.println("clicked");
        Main.creatCurrentQueue(SelectedPlaylistListener.getPlaylistName());


        //cleaning everything
        Main.getJpotifyGUI().getHomePanel().setVisible(true);
        Main.getJpotifyGUI().getHomePanel().removeAll();
        Main.getJpotifyGUI().getContentPane().invalidate();
        Main.getJpotifyGUI().getContentPane().revalidate();
        Main.getJpotifyGUI().getContentPane().repaint();


        //making new panel show up
        PlaylistPanel playlistPanel = new PlaylistPanel(Main.getCurrentQueue());
        Main.getJpotifyGUI().setPlaylistPanel(playlistPanel);
        Main.getJpotifyGUI().getHomePanel().removeAll();
        Main.getJpotifyGUI().getHomePanel().add(Main.getJpotifyGUI().getPlaylistPanel(), BorderLayout.CENTER);
        Main.getJpotifyGUI().revalidate();


        //set this playlist as current queue
        Main.creatCurrentQueue(SelectedPlaylistListener.getPlaylistName());
        Main.setSongQueueIndex(0);
        PlayerManager.PlayerManager();


    }
}
