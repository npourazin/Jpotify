package Controllers;
import GUI.*;

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
    private MusicSliderBar.SliderThread sliderThread;

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
//            Song.getSongPlayThread().start();
            if(ifFirstTimePlaying==0) {
                try {
                    sliderThread = MusicSliderBar.getjSliderThread();
                    sliderThread.setFlag(1);
                    //bayad address file ro az y ja dg biare
                    sP = new SongPlayer("/home/niki/Desktop/ailee - i will show you my-free-mp3s.com .mp3");
//                    sliderThread.
                    sliderThread.start();

                    //Mahvash:  :/AUT/Term2/JpotifyFinalProject/songs/DeanLewis.mp3
                    //Niki: /home/niki/Desktop/ailee - i will show you my-free-mp3s.com .mp3
                } catch (JavaLayerException ex) {
                    ex.printStackTrace();
                }
            }
            else {
                sliderThread.setFlag(1);
                if (sP == null) return;
                sP.resumeSong();
            }
            //niki:
            sliderThread.setFlag(1);
            if (sP == null) return;
            if (sP.ifPlayerNotstarted()) {
                sP.playSong();

            } else {
                sP.resumeSong();
            }

        } else {
            jB.setText(" Play");
            try {
                Image img = ImageIO.read(getClass().getResource("play1.png"));
                jB.setIcon(new ImageIcon(img));
                sP.pauseSong();
                sliderThread.setFlag(0);
                ifFirstTimePlaying=1;

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}