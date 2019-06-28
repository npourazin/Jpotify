package Controllers;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class VolumeChangeListener implements ChangeListener {
    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider volumeSlider = (JSlider) e.getSource();
            float value = volumeSlider.getValue();
            setVolume(value);


    }

    //sets the volume
    public static void setVolume(float value) {

        try {
            Mixer.Info[] infos = AudioSystem.getMixerInfo();
            for (Mixer.Info info : infos) {
                Mixer mixer = AudioSystem.getMixer(info);
                if (mixer.isLineSupported(Port.Info.SPEAKER)) {
                    Port port = (Port) mixer.getLine(Port.Info.SPEAKER);
                    port.open();
                    if (port.isControlSupported(FloatControl.Type.VOLUME)) {
                        FloatControl volume = (FloatControl) port.getControl(FloatControl.Type.VOLUME);
                        volume.setValue(value / 100);
                    }
                    port.close();
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error\n" + ex);
        }
    }


}





