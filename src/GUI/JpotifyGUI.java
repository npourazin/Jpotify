package GUI;

import javax.swing.*;
import java.awt.*;

public class JpotifyGUI extends JFrame {
    public JpotifyGUI(){
        super();
        this.setTitle("Jpotify");
        this.setVisible(true);
        this.setSize(800, 500);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

//        FileChooser fileChooser = new FileChooser();
        ChoicesArea choicesArea = new ChoicesArea();
        choicesArea.setVisible(true);
        this.add(choicesArea, BorderLayout.WEST);


        MusicPanel musicPanel = new MusicPanel();
        this.add(musicPanel, BorderLayout.CENTER);


        //time is to be given here it is given 100 as default
        MusicSliderBar musicSliderBar = new MusicSliderBar(100);
        musicSliderBar.setVisible(true);
        this.add(musicSliderBar,BorderLayout.SOUTH);

//
        JLayer<Component> jLayer = new JLayer<>(musicPanel, new MyLayerUI());
        this.add(jLayer, BorderLayout.CENTER);
        JLayer<Component> jLayer1 = new JLayer<>(choicesArea, new MyLayerUI());
        this.add(jLayer1, BorderLayout.WEST);

        //refreshes the layout after changes
        this.validate();


      //  this.pack();


    }
}
