package GUI;

import Logic.PlayerManager;

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

//        JScrollPane jScrollPane = new JScrollPane(this,   ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

//        jScrollPane.setPreferredSize(new Dimension(600, 600));
        ChoicesArea choicesArea = new ChoicesArea();
        choicesArea.setVisible(true);

        this.add(choicesArea.getjScrollPane(), BorderLayout.WEST);

        HomePanel musicPanel = new HomePanel();
        this.add(musicPanel, BorderLayout.CENTER);


        //time is to be given here it is given 100 as default
        MusicSliderBar musicSliderBar = new MusicSliderBar(100);
        musicSliderBar.setVisible(true);
        this.add(musicSliderBar,BorderLayout.SOUTH);

        JLayer<Component> jLayer = new JLayer<>(musicPanel, new MyLayerUI());
        this.add(jLayer, BorderLayout.CENTER);

//        choicesArea.paint();
        choicesArea.setBackground(Color.cyan);
//        JLayer<Component> jLayer1 = new JLayer<>(choicesArea, new MyLayerUI());
//        this.add(jLayer1, BorderLayout.WEST);

        //refreshes the layout after changes
        this.validate();


      //  this.pack();


    }
}
