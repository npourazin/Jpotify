package Controllers;

import GUI.MusicSliderBar;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ScrollSliderChanger implements ChangeListener {

    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider) e.getSource();
        if (!source.getValueIsAdjusting()) {
            int fps = (int) source.getValue();

            System.out.println("Go to time:" + fps + " of the song");
            //NOW we LITERALY need to go to that time of the song
            MusicSliderBar.getjSliderThread().setCurrentTime(fps);
            double passedPercentage =  (1.0)*fps/MusicSliderBar.getMUSIC_LENGTH();
            //TODO: get the song that is being played fom the Player class and use passedPercentage to adjust the song

        }

    }
}