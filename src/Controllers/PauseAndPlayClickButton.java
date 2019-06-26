package Controllers;

import GUI.ChoicesArea;
import GUI.MusicSliderBar;
import Logic.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PauseAndPlayClickButton implements ActionListener {
    private static SongPlayer sP;
    private static boolean ifFirstTimePlaying = true;
    private static boolean ifNewSong=true;
    private static boolean buttonPlaying = false;
    private static boolean endOfSong=false;
    private static SliderThread sliderThread;

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jB = (JButton) (e.getSource());
        if(endOfSong){

        }
        if (jB.getText().equals(" Play") || endOfSong) {
            if(jB.getText().equals(" Play")) {
                buttonPlaying = true;
                jB.setText("Pause");
                try {
                    Image img = ImageIO.read(getClass().getResource("pause1.png"));
                    jB.setIcon(new ImageIcon(img));
                    sP = PlayerManager.getsP();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            //The fucking method
            setSongToPlay();

        } else {
            buttonPlaying = false;
            jB.setText(" Play");
            try {
                Image img = ImageIO.read(getClass().getResource("play1.png"));
                jB.setIcon(new ImageIcon(img));
                sP.pauseSong();
                sliderThread.setFlag(0);
                ifFirstTimePlaying = false;

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void setSongToPlay(){
        buttonPlaying = true;
        if (ifFirstTimePlaying) {
            sliderThread = MusicSliderBar.getjSliderThread();
            sliderThread.setFlag(1);
            sliderThread.start();
            ifFirstTimePlaying=false;

        }
        if(ifNewSong && !ifFirstTimePlaying){

            //song cover icon
            System.out.println(Main.getCurrentQueue().get(Main.getSongQueueIndex()));
            MusicSliderBar.getSongIconLable().setIcon(new ImageIcon(((ImageIcon) Main.getCurrentQueue().get(Main.getSongQueueIndex()).getIcon()).getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT)));
            MusicSliderBar.getSongIconLable().setText(Main.getCurrentQueue().get(Main.getSongQueueIndex()).getSongName());
            System.out.println(sliderThread.getState());
            sliderThread.setCurrentTime(0);
            sliderThread.setFlag(1);
            sP.pauseSong();
            sP=null;
            sP = PlayerManager.getsP();
            MusicSliderBar.getJSlider().setValue(0);
            MusicSliderBar.setMusicLength(Main.getCurrentQueue().get(Main.getSongQueueIndex()).getMusicLength());
            MusicSliderBar.getJSlider().setMaximum((int) Main.getCurrentQueue().get(Main.getSongQueueIndex()).getMusicLength());

            //WHAT THE ACTUAL FUUUUUCK!!!!!!  WHY CANT YOU SET WHAT YOU ARE SHOWING MEEEE!!?????
            //TODO: fix this shit

            System.out.println((int) MusicSliderBar.getMusicLenght());
//                MusicSliderBar.getJSlider().setMajorTickSpacing((int) MusicSliderBar.getMusicLenght());
//                MusicSliderBar.getJSlider().setMinorTickSpacing((int) MusicSliderBar.getMusicLenght());

            MusicSliderBar.getJSlider().setMajorTickSpacing(1000);
            ifNewSong=false;
            endOfSong=false;
            ifFirstTimePlaying=false;
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

    }

    public static boolean getIfFirstTimePlaying(){
        return ifFirstTimePlaying;
    }

    public static void setEndOfSong(){
        endOfSong=true;
    }
    public static SongPlayer getSongCurrentPlayer() {
        return sP;
    }
    public static void setsP(SongPlayer songPlayer){
        sP=songPlayer;
    }

    public static void setIfFirstTimePlaying(){
        ifFirstTimePlaying=true;
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