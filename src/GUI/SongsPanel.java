package GUI;

import Logic.SongData;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public class SongsPanel extends JPanel {
    private ArrayList<SongData> songs;
    private ArrayList<JButton> songButton;
    private JScrollPane jScrollPane;

    public SongsPanel(ArrayList<SongData> songs) {
        super();

        //add scroller
        jScrollPane = new JScrollPane(this);
        jScrollPane.setViewportView(this);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setViewportBorder(new LineBorder(Color.pink));
        jScrollPane.updateUI();
        jScrollPane.setVisible(true);
        this.setLayout(new FlowLayout());

        this.songs = new ArrayList<>();
        this.songs = songs;
        System.out.println(songs.size());
        songButton = new ArrayList<>();
        this.setVisible(true);
        Dimension d = new Dimension(200, 200);
        System.out.println("song size:"+songs.size());
        for (int i = 0; i < songs.size(); i++) {
            JButton j = new JButton();
            j.setPreferredSize(d);
            songButton.add(j);
            songButton.get(i).setLayout(new BorderLayout());
            this.add(songButton.get(i));
            songButton.get(i).setVisible(true);
            if( songs.get(i).getIcon()!=null)
                 songButton.get(i).setIcon(new ImageIcon(((ImageIcon) songs.get(i).getIcon()).getImage().getScaledInstance(130, 130, Image.SCALE_DEFAULT)));
            if(songs.get(i).getSongName()!=null){
                JTextArea a = new JTextArea(songs.get(i).getSongName());
                songButton.get(i).add(a, BorderLayout.SOUTH);

            }
            else{
                JTextArea a = new JTextArea("UNKNOWN");
                songButton.get(i).add(a, BorderLayout.SOUTH);
            }
                //            songButton.get(i).setText("Artist:"+songs.get(i).getArtist()+"\n");
//            songButton.get(i).setText("Album:"+songs.get(i).getAlbum());
        }


        //TODO: give each button a listener to play the song
//        for (int i = 0; i <6 ; i++) {
//            JButton j=new JButton();
//            songButton.add(j);
//            ImageIcon mg=new ImageIcon("D:\\AUT\\Term2\\JpotifyFinalProject\\images\\songCover.jpg");
//            songButton.get(i).setIcon(mg);
//            this.add(j);
//            songButton.get(i).setVisible(true);
//
//        }
    }


}
