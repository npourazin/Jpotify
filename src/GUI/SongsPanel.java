package GUI;

import Logic.SongData;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SongsPanel extends JPanel {
    private ArrayList<SongData> songs;
    private static int numberOfSongs = 0;
    private ArrayList<JButton> songButton;
    public SongsPanel(ArrayList<SongData> songs){
        super();
        this.setLayout(new FlowLayout());
        this.songs=new ArrayList<>();
        this.songs = songs;
        System.out.println(songs.size());
        songButton=new ArrayList<>();
        this.setVisible(true);
        for (int i = 0; i <songs.size() ; i++) {
            JButton j=new JButton();
            songButton.add(j);
            this.add(songButton.get(i));
            songButton.get(i).setVisible(true);
            songButton.get(i).setIcon(songs.get(i).getIcon());

        }
    }
}
