package Controllers;

//import GUI.PlaylistPanel;
import GUI.SongsPanel;
import Logic.Main;
import Logic.PlayerManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ItemClickedShowPlaylist implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println(SelectedPlaylistListener.getPlaylistName());
        System.out.println("clicked");
        Main.creatCurrentQueueByAdd(SelectedPlaylistListener.getPlaylistName());


        //cleaning everything
        Main.getJpotifyGUI().getHomePanel().setVisible(true);
        Main.getJpotifyGUI().getHomePanel().removeAll();
        Main.getJpotifyGUI().getContentPane().invalidate();
        Main.getJpotifyGUI().getContentPane().revalidate();
        Main.getJpotifyGUI().getContentPane().repaint();


        //making new panel show up
        SongsPanel playlistPanel = new SongsPanel(Main.getCurrentQueue());
        Main.getJpotifyGUI().setSongsPanel(playlistPanel);
        Main.getJpotifyGUI().getHomePanel().removeAll();
        Main.getJpotifyGUI().getHomePanel().add(Main.getJpotifyGUI().getSongsPanel(), BorderLayout.CENTER);
        Main.getJpotifyGUI().revalidate();


        //set this playlist as current queue
        Main.creatCurrentQueueByAdd(SelectedPlaylistListener.getPlaylistName());
        Main.setSongQueueIndex(0);
        PlayerManager.playerManager();


    }
}
