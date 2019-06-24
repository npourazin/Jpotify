package GUI;

import Controllers.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class MusicSliderBar extends JPanel {
    private static SliderThread jSliderThread;
    private static JSlider jSlider;
    private static long musicLength;
    private long MUSIC_LENGHT;

    private JButton previousButton;
    private JButton playButton;
    private JButton nextButton;
    private JButton replayButton;
    private static JTextArea showTime;

    //    private Thread jSliderThread;
    MusicSliderBar(long musicLength) {
        super();
        this.setBackground(Color.cyan);
        this.setLayout(new GridLayout(2, 1));
        //TODO: get music length in seconds. (now it was just set to 100)
        MUSIC_LENGHT = musicLength;
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.cyan);
        topPanel.setLayout(new FlowLayout());
        this.add(topPanel);

        //Creating previous button
        previousButton = new JButton();
        previousButton.setVisible(true);
        previousButton.setBackground(Color.cyan);
        previousButton.addActionListener(new PreviousButtonListener());
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

        //Creating stop button
        JButton stopButton = new JButton("Stop");
        stopButton.setVisible(true);
        stopButton.setBackground(Color.cyan);
        topPanel.add(stopButton);
        stopButton.addActionListener(new StopButtonListener());
        try {
            Image img = ImageIO.read(getClass().getResource("stop1.png"));
            stopButton.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        //Creating next button
        nextButton = new JButton();
        nextButton.setVisible(true);
        nextButton.setBackground(Color.cyan);
        nextButton.addActionListener(new NextButtonListener());
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
        jSlider = new JSlider(0, (int) MUSIC_LENGHT, 0);
        jSlider.setVisible(true);
//        jSlider.setMinorTickSpacing(1);
//        jSlider.setMajorTickSpacing(10);
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
        showTime.setFont(showTime.getFont().deriveFont(16f)); // will only change size to 16pt
        showTime.setBackground(Color.cyan);
        showTime.setVisible(true);


        //Creating image icon
        //TO DO -->  get song cover image
//        ImageIcon imageIcon=new ImageIcon("images/songCover.jpg");
//        JLabel imageLable=new JLabel(imageIcon);
//        topPanel.add(imageLable);

//        JLabel currentSongLable = new JLabel(song.getArtist(), song.getIcon(), JLabel.CENTER);


        //Thread for Sliding
        //TODO: make a separate class for the thread and have "current" as its local variable so it can be also stopped.
        jSliderThread = new SliderThread();


    }

    public static JTextArea getShowTime() {
        return showTime;
    }

    public static void setMusicLength(long length) {
        musicLength =length;
    }

    public static JSlider getJSlider() {
        return jSlider;
    }

    public static long getMusicLenght() {
        return musicLength;
    }

    public static SliderThread getjSliderThread() {
        return jSliderThread;
    }


}






