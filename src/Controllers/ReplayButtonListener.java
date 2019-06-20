package Controllers;

import GUI.MusicSliderBar;
import Logic.SongPlayer;
import javazoom.jl.decoder.JavaLayerException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReplayButtonListener implements ActionListener {
    private SongPlayer sP;

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jB = (JButton) (e.getSource());
        try {
            //bayad address file ro az y ja dg biare
            sP = new SongPlayer("/AUT/Term2/JpotifyFinalProject/songs/DeanLewis.mp3");
            sP.playSong();
            //Mahvash:  :/AUT/Term2/JpotifyFinalProject/songs/DeanLewis.mp3
            //Niki: /home/niki/Desktop/ailee - i will show you my-free-mp3s.com .mp3
        } catch (JavaLayerException ex) {
            ex.printStackTrace();
        }

    }
}
