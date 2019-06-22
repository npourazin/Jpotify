package Controllers;

import GUI.MusicSliderBar;
import Logic.PlayerManager;
import Logic.SongPlayer;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.io.IOException;

public class ScrollSliderChanger implements ChangeListener {
    private static int prev=0;
    private static int next=0;
    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider) e.getSource();
        Mp3File file = null;
        SongPlayer sP;
        if (!source.getValueIsAdjusting()) {
            int fps = (int) source.getValue();
            sP = PlayerManager.getsP();
            try {
                file = new Mp3File(sP.getFileName());
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
            if(next==0){
                next=fps;
            }else{
                prev = next;
                next=fps;
            }

            double passedPercentage = (1.0) * fps / MusicSliderBar.getMUSIC_LENGTH();
//            if (!MusicSliderBar.getJSlider().getValueIsAdjusting()) {
//                MusicSliderBar.getjSliderThread().setCurrentTime(MusicSliderBar.getjSliderThread().getCurrentTime()-1);
//            }

//            if (!PauseAndPlayClickButton.ifButtonPlaying())
            if((Math.abs(next - prev)>1))
                sP.playInMiddle((int) (frames * passedPercentage));
            else if((Math.abs(next - prev)==1) && MusicSliderBar.getjSliderThread().getFlag()==0 )
                sP.playInMiddle((int) (frames * passedPercentage));

        }

    }
}