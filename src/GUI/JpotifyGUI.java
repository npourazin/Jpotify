package GUI;

import javax.swing.*;
import java.awt.*;

public class JpotifyGUI extends JFrame {
    public JpotifyGUI(){
        super();
        this.setTitle("Jpotify");
        this.setVisible(true);
        this.setSize(400, 400);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);


        MusicPanel musicPanel = new MusicPanel();
        this.add(musicPanel, BorderLayout.NORTH);


        //time is to be given here it is given 100 as default
        MusicSliderBar musicSliderBar = new MusicSliderBar(100);
        musicSliderBar.setVisible(true);
        this.add(musicSliderBar, BorderLayout.SOUTH);


        JLayer<Component> jLayer = new JLayer<>(musicPanel, new MyLayerUI());
        this.add(jLayer);
        JLayer<Component> jLayer1 = new JLayer<>(musicSliderBar, new MyLayerUI());
        this.add(jLayer1);


        //refreshes the layout after changes
        this.validate();


      //  this.pack();


    }
}
