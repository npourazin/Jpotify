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
    private static JSlider jSlider;
    private final int MUSIC_LENGTH;
    private JButton previousButton;
    private JButton playButton;
    private JButton nextButton;
    private JButton replayButton;
    private JTextArea showTime;

    //    private Thread jSliderThread;
    MusicSliderBar(int musicLength) {
        super();
        this.setBackground(Color.cyan);
        this.setLayout(new GridLayout(2, 1));
        //TODO: get music length in seconds. (now it was just set to 100)
        MUSIC_LENGTH = musicLength;
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.cyan);
        topPanel.setLayout(new FlowLayout());
        this.add(topPanel);

        //Creating previous button
        previousButton = new JButton();
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
        playButton = new JButton();
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
        nextButton = new JButton();
        nextButton.setVisible(true);
        nextButton.setBackground(Color.cyan);
        topPanel.add(nextButton);
        try {
            Image img = ImageIO.read(getClass().getResource("next1.png"));
            nextButton.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        //Creating replay button
        replayButton = new JButton("Replay");
        replayButton.setVisible(true);
        replayButton.setBackground(Color.cyan);
        topPanel.add(replayButton);
        replayButton.addActionListener(new ReplayButtonListener());
        try {
            Image img = ImageIO.read(getClass().getResource("replay1.png"));
            replayButton.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        //Creating jSlider
        jSlider = new JSlider(0, MUSIC_LENGTH, 0);
        jSlider.setVisible(true);
        jSlider.setMinorTickSpacing(MUSIC_LENGTH);
        jSlider.setMajorTickSpacing(MUSIC_LENGTH);
        jSlider.setPaintTicks(true);
        jSlider.setPaintLabels(true);
//        jSlider.setValueIsAdjusting(true);
        jSlider.addChangeListener(new ScrollSliderChanger());
        jSlider.setBackground(Color.cyan);
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.cyan);
        bottomPanel.setLayout(new BorderLayout());
        this.add(bottomPanel);
        bottomPanel.add(jSlider, BorderLayout.CENTER);

        //Creating a text field to show time
        showTime = new JTextArea("00:00/00:00");
        bottomPanel.add(showTime, BorderLayout.EAST);
        showTime.setBackground(Color.cyan);
        showTime.setVisible(false);



        //Creating image icon
        //TO DO -->  get song cover image
//        ImageIcon imageIcon=new ImageIcon("images/songCover.jpg");
//        JLabel imageLable=new JLabel(imageIcon);
//        topPanel.add(imageLable);

        //Thread for Sliding
        //TODO: make a separate class for the thread and have "current" as its local variable so it can be also stopped.
        jSliderThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int currentTime = 0;
                while (currentTime <= MUSIC_LENGTH) {
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


    }

    public static JSlider getJSlider() {
        return jSlider;
    }

    public static Thread getjSliderThread() {
        return jSliderThread;
    }

}




