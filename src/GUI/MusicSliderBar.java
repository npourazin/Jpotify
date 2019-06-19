package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MusicSliderBar extends MusicPanel {
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
        jSlider.addChangeListener(new ScrollChange());
        jSlider.setBackground(Color.cyan);
        this.add(jSlider);

        //Creating Play/Pause button
        JButton jButton = new JButton();
        jButton.setVisible(true);
        jButton.setText(" Play");
        jButton.addActionListener(new clickButton());
        this.add(jButton);
        try {
            Image img = ImageIO.read(getClass().getResource("play1.png"));
            jButton.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println(ex);
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

class ScrollChange implements ChangeListener {

    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider) e.getSource();
        if (!source.getValueIsAdjusting()) {
            int fps = (int) source.getValue();

            System.out.println("Go to time:" + fps + " of the song");
            //NOW we LITERALY need to go to that time of the song
        }

    }
}

class clickButton implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jB = (JButton) (e.getSource());
        if (jB.getText().equals(" Play")) {
            jB.setText("Pause");
            try {
                Image img = ImageIO.read(getClass().getResource("pause1.png"));
                jB.setIcon(new ImageIcon(img));
            } catch (Exception ex) {
                System.out.println(ex);
            }

        } else {
            jB.setText(" Play");
            try {
                Image img = ImageIO.read(getClass().getResource("play1.png"));
                jB.setIcon(new ImageIcon(img));

            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }
}
