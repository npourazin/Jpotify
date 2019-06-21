package Controllers;

import GUI.MusicSliderBar;
import Logic.Player;
import Logic.SongPlayer;
import javazoom.jl.decoder.JavaLayerException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReplayButtonListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jB = (JButton) (e.getSource());
        //bayad address file ro az y ja dg biare
        SongPlayer sP = Player.getsP();
        sP.stopSong();
//        try {
//            sP = new SongPlayer("/AUT/Term2/JpotifyFinalProject/songs/DeanLewis.mp3");
//        } catch (JavaLayerException ex) {
//            ex.printStackTrace();
//        }

        //Mahvash:  :/AUT/Term2/JpotifyFinalProject/songs/DeanLewis.mp3
        //Niki: /home/niki/Desktop/ailee - i will show you my-free-mp3s.com .mp3

    }
}
