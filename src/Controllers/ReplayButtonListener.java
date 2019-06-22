package Controllers;

import Logic.PlayerManager;
import Logic.SongPlayer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReplayButtonListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jB = (JButton) (e.getSource());
        //bayad address file ro az y ja dg biare
        SongPlayer sP = PlayerManager.getsP();
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
