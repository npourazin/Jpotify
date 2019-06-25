package GUI;

import Logic.SongData;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PlaylistPanel extends JPanel{
    private ArrayList<SongData> songs;
    private ArrayList<JButton> songButton;
    public PlaylistPanel(ArrayList<SongData> songsArraylist){
        this.songs=new ArrayList<SongData>();
        this.songs=songsArraylist;
        System.out.println(songs.size());
        songButton = new ArrayList<>();
        Dimension d = new Dimension(200, 200);
        for (int i = 0; i < songs.size(); i++) {
            JButton j = new JButton();
            j.setPreferredSize(d);
            songButton.add(j);
            this.add(songButton.get(i));
            songButton.get(i).setVisible(true);
            songButton.get(i).setIcon(new ImageIcon(((ImageIcon) songs.get(i).getIcon()).getImage().getScaledInstance(130, 130, Image.SCALE_DEFAULT)));
            songButton.get(i).setText(songs.get(i).getSongName());
//            songButton.get(i).setText("Artist:"+songs.get(i).getArtist()+"\n");
//            songButton.get(i).setText("Album:"+songs.get(i).getAlbum());
        };
    }
}
