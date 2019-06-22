package GUI;

import Logic.SongData;
import Logic.SongPlayer;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.LayerUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MusicPanel extends JPanel {
    private ArrayList<SongData> songs;
    private static int numberOfSongs = 0;
    private ArrayList<JButton> songButton;

    MusicPanel(ArrayList<SongData> songs) {
        super();
        this.songs=new ArrayList<>();
        this.songs = songs;
        System.out.println(songs.size());
        songButton=new ArrayList<>();
        this.setVisible(true);
//        int i=0;
//        for (SongData song : this.songs) {
//            this.setLayout(new GridLayout(3,3));
//            songButton[i] = new JButton(song.getArtist());
//            songButton[i].setVisible(true);
//            this.add(songButton[i]);
//            songButton[i].setIcon(song.getIcon());
//            i++;
//            System.out.println(i);
//        }
        this.setLayout(new FlowLayout());

        for (int i = 0; i <songs.size() ; i++) {
            JButton j=new JButton();
            songButton.add(j);
            this.add(songButton.get(i));
            songButton.get(i).setVisible(true);
            songButton.get(i).setIcon(songs.get(i).getIcon());

        }

//        this.createPanel();
//        this.setSize(800, 800);
//        this.add(new MusicSliderBar());
    }

//    public void addSong(SongData songData) {
//        numberOfSongs++;
//        ImageIcon icon = (ImageIcon) songData.getIcon();
//        label1 = new JLabel("Image and Text", icon, JLabel.CENTER);
//...
//        label3 = new JLabel(icon);
//    }

    public void createPanel() {

        ButtonGroup entreeGroup = new ButtonGroup();
        JRadioButton radioButton;
        this.add(radioButton = new JRadioButton("what", true));
        entreeGroup.add(radioButton);
        this.add(radioButton = new JRadioButton("the"));
        entreeGroup.add(radioButton);
        this.add(radioButton = new JRadioButton("heeeeell"));
        entreeGroup.add(radioButton);

        this.add(new JCheckBox("kooft"));
        this.add(new JCheckBox("dard"));
        this.add(new JCheckBox("maraz"));

        this.add(new JLabel("Special requests:"));
        this.add(new JTextField(20));

        JButton orderButton = new JButton("Send Message");
        this.add(orderButton);

    }
}

//class MyActionListoner implements ActionListener {
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        System.out.println("boogh");
//    }
//}
