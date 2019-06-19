package GUI;
import Controllers.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MusicSliderBar extends JPanel {
    private final int MUSIC_LENGTH;

    MusicSliderBar(int musicLength) {
        super();
        this.setBackground(Color.cyan);
        MUSIC_LENGTH = musicLength;
        //Creating jSlider
        JSlider jSlider = new JSlider(0, MUSIC_LENGTH, 0);
        jSlider.setVisible(true);
        jSlider.setMinorTickSpacing(MUSIC_LENGTH);
        jSlider.setMajorTickSpacing(MUSIC_LENGTH);
        jSlider.setPaintTicks(true);
        jSlider.setPaintLabels(true);
        jSlider.addChangeListener(new ScrollSliderChanger());
        jSlider.setBackground(Color.cyan);
        this.add(jSlider);

        //Creating Play/Pause button
        JButton jButton = new JButton();
        jButton.setVisible(true);
        jButton.setText(" Play");
        jButton.addActionListener(new PauseAndPlayClickButton());
        this.add(jButton);
        try {
            Image img = ImageIO.read(getClass().getResource("play1.png"));
            jButton.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        jButton.setBackground(Color.cyan);
        new Thread(new Runnable() {
            public void run() {
                int currentTime = 0;
                while (currentTime < MUSIC_LENGTH) {
                    jSlider.setValue(currentTime);
                }
            }
        }).start();
    }
}




