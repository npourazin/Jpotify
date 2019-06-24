package GUI;

import Logic.SongData;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public class SongsPanel extends JPanel {
    private ArrayList<SongData> songs;
    private ArrayList<JButton> songButton;
    private static boolean visible=true;
private JScrollPane jScrollPane;
    public SongsPanel(ArrayList<SongData> songs){
        super();
        jScrollPane = new JScrollPane(this);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setViewportBorder(new LineBorder(Color.pink));
        jScrollPane.setBackground(Color.cyan);
        jScrollPane.updateUI();
        jScrollPane.setVisible(true);
        this.setLayout(new FlowLayout());
        this.songs=new ArrayList<>();
        this.songs = songs;
        System.out.println(songs.size());
        songButton=new ArrayList<>();
        this.setVisible(visible);
        Dimension d=new Dimension(200,200);
        //TODO: make this uncomment when you had a proper array list and compelete the following code.
        for (int i = 0; i <songs.size() ; i++) {
            JButton j=new JButton();
            j.setPreferredSize(d);
            songButton.add(j);
            this.add(songButton.get(i));
            songButton.get(i).setVisible(true);
            songButton.get(i).setIcon(songs.get(i).getIcon());

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
    public static void setSongsPanelNotVisible(){
        visible=false;
    }
    public JScrollPane getjScrollPane() {
        return jScrollPane;
    }

}
