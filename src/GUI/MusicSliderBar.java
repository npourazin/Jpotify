package GUI;

import Controllers.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

public class MusicSliderBar extends JPanel {
    private static Thread jSliderThread;
    private final int MUSIC_LENGTH;

    //    private Thread jSliderThread;
    MusicSliderBar(int musicLength) {
        super();
        this.setBackground(Color.cyan);
        this.setLayout(new GridLayout(2,1));
        MUSIC_LENGTH = musicLength;
        JPanel topPanel=new JPanel();
        topPanel.setBackground(Color.cyan);
        topPanel.setLayout(new FlowLayout());
        this.add(topPanel);

        //Creating previous button
        JButton previousButton=new JButton();
        previousButton.setVisible(true);
        previousButton.setBackground(Color.cyan);
        topPanel.add(previousButton);
        try {
            Image img = ImageIO.read(getClass().getResource("previous1.png"));
            previousButton.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        //Creating Play/Pause button
        JButton playButton = new JButton();
        playButton.setVisible(true);
        playButton.setText(" Play");
        playButton.addActionListener(new PauseAndPlayClickButton());
        topPanel.add(playButton);
        try {
            Image img = ImageIO.read(getClass().getResource("play1.png"));
            playButton.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        playButton.setBackground(Color.cyan);

        //Creating next button
        JButton nextButton=new JButton();
        nextButton.setVisible(true);
        nextButton.setBackground(Color.cyan);
        topPanel.add(nextButton);
        try {
            Image img = ImageIO.read(getClass().getResource("next1.png"));
            nextButton.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        //Creating jSlider
        JSlider jSlider = new JSlider(0, MUSIC_LENGTH, 0);
        jSlider.setVisible(true);
        jSlider.setMinorTickSpacing(MUSIC_LENGTH);
        jSlider.setMajorTickSpacing(MUSIC_LENGTH);
        jSlider.setPaintTicks(true);
        jSlider.setPaintLabels(true);
//        jSlider.setValueIsAdjusting(true);
        jSlider.addChangeListener(new ScrollSliderChanger());
        jSlider.setBackground(Color.cyan);
        this.add(jSlider);


        //Thread for Sliding
        jSliderThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int currentTime = 0;
                while (currentTime < MUSIC_LENGTH) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    jSlider.setValue(currentTime);
                    currentTime++;
                }
            }
        });
        jSliderThread.start();


    }

    public static Thread getjSliderThread() {
        return jSliderThread;
    }

}




