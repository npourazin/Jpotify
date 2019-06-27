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

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx=0;
        gbc.gridy=0;

        Dimension d = new Dimension(160, 160);
        for (int i = 0; i < songs.size(); i++) {
            JButton j = new JButton();
            j.setPreferredSize(d);
            songButton.add(j);
            songButton.get(i).setLayout(new BorderLayout());
            this.add(songButton.get(i), gbc);
            songButton.get(i).setVisible(true);
            if( songs.get(i).getIcon()!=null)
                songButton.get(i).setIcon(new ImageIcon(((ImageIcon) songs.get(i).getIcon()).getImage().getScaledInstance(130, 130, Image.SCALE_DEFAULT)));
            if(songs.get(i).getSongName()!=null){
                JTextArea a = new JTextArea(songs.get(i).getSongName());
                songButton.get(i).add(a, BorderLayout.SOUTH);
            }
            else{
                JTextArea a = new JTextArea("UNKNOWN");
                songButton.get(i).add(a, BorderLayout.NORTH);

            }
            gbc.gridx++;
            if (gbc.gridx==3){
                gbc.gridx=0;
                gbc.gridy++;
            }
//            this.repaint();
//            this.revalidate();

        }
    }
}
