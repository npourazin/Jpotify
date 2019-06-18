package GUI;

import javax.swing.*;
import java.awt.*;

public class JpotifyGUI extends JFrame {
    public JpotifyGUI(){
        super();
        this.setTitle("Jpotify");
        this.setVisible(true);
        this.setSize(200, 200);
        this.pack();
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
