package Controllers;

import GUI.MusicSliderBar;
import Logic.Player;
import Logic.SongPlayer;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.io.IOException;

public class ScrollSliderChanger implements ChangeListener {

    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider) e.getSource();
        Mp3File file = null;
        SongPlayer sP;
        if (!source.getValueIsAdjusting()) {
            int fps = (int) source.getValue();
            sP = Player.getsP();
            try {
                file=new Mp3File(sP.getFileName());
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (UnsupportedTagException ex) {
                ex.printStackTrace();
            } catch (InvalidDataException ex) {
                ex.printStackTrace();
            }
            int frames = file.getFrameCount();
            System.out.println("Go to time:" + fps + " of the song");
            //NOW we LITERALY need to go to that time of the song
            MusicSliderBar.getjSliderThread().setCurrentTime(fps);
            double passedPercentage = (1.0) * fps / MusicSliderBar.getMUSIC_LENGTH();
            if (!PauseAndPlayClickButton.ifButtonPlaying())
//            if (!MusicSliderBar.getJSlider().getValueIsAdjusting())return;
                sP.playInMiddle((int) (frames*passedPercentage));
        }

    }
}