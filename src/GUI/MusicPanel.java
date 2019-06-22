package GUI;

import Logic.SongData;
import Logic.SongPlayer;

import javax.swing.*;
import javax.swing.plaf.LayerUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MusicPanel extends JPanel {
    private ArrayList<SongData> songs;
    private static int numberOfSongs = 0;

    MusicPanel(ArrayList<SongData> songs) {
        super();
        this.songs = songs;
        this.setVisible(true);
        JLabel[] label=new JLabel[songs.size()];
        int i=0;
        for (SongData song : songs) {
            label[i] = new JLabel(song.getArtist(), song.getIcon(), JLabel.CENTER);

            i++;
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
