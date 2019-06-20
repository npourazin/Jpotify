package Controllers;
//import GUI.*;

import GUI.MusicSliderBar;
import Logic.Song;
import Logic.SongPlayer;
import javazoom.jl.decoder.JavaLayerException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PauseAndPlayClickButton implements ActionListener {
    private SongPlayer sP;
    private static int ifFirstTimePlaying=0;

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jB = (JButton) (e.getSource());
        if (jB.getText().equals(" Play")) {
            jB.setText("Pause");
            try {
                Image img = ImageIO.read(getClass().getResource("pause1.png"));
                jB.setIcon(new ImageIcon(img));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
//            MusicSliderBar.getjSliderThread().start();
//            Song.getSongPlayThread().start();
            if(ifFirstTimePlaying==0) {
                try {
                    //bayad address file ro az y ja dg biare
                    sP = new SongPlayer("/AUT/Term2/JpotifyFinalProject/songs/DeanLewis.mp3");
                    //Mahvash:  :/AUT/Term2/JpotifyFinalProject/songs/DeanLewis.mp3
                    //Niki: /home/niki/Desktop/ailee - i will show you my-free-mp3s.com .mp3
                } catch (JavaLayerException ex) {
                    ex.printStackTrace();
                }
            }
            else sP.resumeSong();
            //niki:
            if (sP == null) return;
            if (sP.ifPlayerNotstarted()) {
                sP.playSong();
            } else {
                sP.resumeSong();
            }

        } else {
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException ex) {
//                ex.printStackTrace();
//            }

            jB.setText(" Play");
            try {
                Image img = ImageIO.read(getClass().getResource("play1.png"));
                jB.setIcon(new ImageIcon(img));
                sP.pauseSong();
                ifFirstTimePlaying=1;

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}