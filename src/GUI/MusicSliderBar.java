package GUI;

import javax.swing.*;

class MusicSliderBar extends MusicPanel {
    private final int MUSIC_LENGTH;
    MusicSliderBar(int musicLength){
        super();
        MUSIC_LENGTH = musicLength;
        JSlider jSlider  = new JSlider(0, MUSIC_LENGTH, 0);
        jSlider.setVisible(true);
        jSlider.setMinorTickSpacing(MUSIC_LENGTH);
        jSlider.setMajorTickSpacing(MUSIC_LENGTH);
        jSlider.setPaintTicks(true);
        jSlider.setPaintLabels(true);
        this.add(jSlider);
    }
}
