package Controllers;

import Logic.Main;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.Decoder;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.FactoryRegistry;
import javazoom.jl.player.JavaSoundAudioDevice;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.io.IOException;

public class VolumeChangeListener implements ChangeListener {
    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider volumeSlider = (JSlider) e.getSource();
        if(!volumeSlider.getValueIsAdjusting()) {
            int value = volumeSlider.getValue();

//            Bitstream bitstream = new Bitstream(conn.getInputStream()/*new FileInputStream(quick_file)*/);
//            System.out.println(bitstream);
//            Decoder decoder = new Decoder();
//            decoder.setEqualizer(equalizer);
            JavaSoundAudioDevice audio = null;
            try {
                 audio = (JavaSoundAudioDevice) FactoryRegistry.systemRegistry().createAudioDevice();
            } catch (JavaLayerException ex) {
                ex.printStackTrace();
            }
            JavaSoundAudioDevice jsAudio = audio;
            jsAudio.setLineGain(value);
            System.out.println(value);
        }
    }
}
