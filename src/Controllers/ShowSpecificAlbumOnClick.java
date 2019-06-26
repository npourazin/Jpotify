package Controllers;

import GUI.SongsPanel;
import Logic.Main;
import Logic.PlayerManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowSpecificAlbumOnClick implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {

        JButton jB = (JButton) (e.getSource());
        System.out.println(jB.getName());
        Main.creatCurrentQueueByAdd(jB.getName());


        //cleaning everything
        Main.getJpotifyGUI().getHomePanel().setVisible(true);
        Main.getJpotifyGUI().getHomePanel().removeAll();
        Main.getJpotifyGUI().getContentPane().invalidate();
        Main.getJpotifyGUI().getContentPane().revalidate();
        Main.getJpotifyGUI().getContentPane().repaint();


        //making new panel show up
        SongsPanel albumPlaylistPanel = new SongsPanel(Main.getCurrentQueue());
        Main.getJpotifyGUI().setSongsPanel(albumPlaylistPanel);
        Main.getJpotifyGUI().getHomePanel().removeAll();
        Main.getJpotifyGUI().getHomePanel().add(Main.getJpotifyGUI().getSongsPanel(), BorderLayout.CENTER);
        Main.getJpotifyGUI().revalidate();


        //set this playlist as current queue
        Main.setSongQueueIndex(0);
        PlayerManager.playerManager();

    }
}
