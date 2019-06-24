package Controllers;

import GUI.MusicSliderBar;
import Logic.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PauseAndPlayClickButton implements ActionListener {
    private static SongPlayer sP;
    private static int ifFirstTimePlaying = 0;
    private static boolean ifNewSong=true;
    private SliderThread sliderThread;
    private static boolean buttonPlaying = false;

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jB = (JButton) (e.getSource());
        if (jB.getText().equals(" Play")) {
            buttonPlaying = true;
            jB.setText("Pause");
            try {
                Image img = ImageIO.read(getClass().getResource("pause1.png"));
                jB.setIcon(new ImageIcon(img));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            if (ifFirstTimePlaying == 0) {
                sliderThread = MusicSliderBar.getjSliderThread();
                sliderThread.setFlag(1);
                sliderThread.start();
            }
            if(ifNewSong){
                sliderThread.setCurrentTime(0);
                sliderThread.setFlag(1);
                sP = PlayerManager.getsP();
                MusicSliderBar.getJSlider().setValue(0);
                MusicSliderBar.setMusicLength(Main.getCurrentQueue().get(Main.getSongQueueIndex()).getMusicLength());
                MusicSliderBar.getJSlider().setMaximum((int) MusicSliderBar.getMusicLenght());

                //WHAT THE ACTUAL FUUUUUCK!!!!!!  WHY CANT YOU SET WHAT YOU ARE WHOWING MEEEE!!?????
                //TODO: fix this shit

                System.out.println((int) MusicSliderBar.getMusicLenght());
                MusicSliderBar.getJSlider().setMajorTickSpacing((int) MusicSliderBar.getMusicLenght());
//                MusicSliderBar.getJSlider().setMinorTickSpacing((int) MusicSliderBar.getMusicLenght());
                ifNewSong=false;
            }
            else {
                sliderThread.setFlag(1);
                if (sP == null) {
                    return;
                }
                sP.resumeSong();
            }





            //niki:
            sliderThread.setFlag(1);
            if (sP == null) {
                System.out.println("NULL");
                return;
            }
            if (sP.ifPlayerNotstarted()) {
                sP.playSong();
            } else {
                if(ScrollSliderChanger.ifSliderChangedWhilePaused()){
                    sP.pauseSong();
                    sP.playInMiddle((int) (ScrollSliderChanger.getFrames() * ScrollSliderChanger.getPassedPercentage()));
                }
                else sP.resumeSong();
            }

        } else {
            buttonPlaying = false;
            jB.setText(" Play");
            try {
                Image img = ImageIO.read(getClass().getResource("play1.png"));
                jB.setIcon(new ImageIcon(img));
                sP.pauseSong();
                sliderThread.setFlag(0);
                ifFirstTimePlaying = 1;

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static SongPlayer getSongCurrentPlayer() {
        return sP;
    }
    public static void setsP(SongPlayer songPlayer){
        sP=songPlayer;
    }

    public static void setIfFirstTimePlaying(){
        ifFirstTimePlaying=0;
    }

    public static boolean getIfNewSong(){
        return ifNewSong;
    }

    public static void setIfNewSong(){
        ifNewSong=true;
    }

    public static boolean ifButtonPlaying() {
        return buttonPlaying;
    }
}