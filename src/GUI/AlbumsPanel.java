package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AlbumsPanel extends JPanel {

    private ArrayList<JButton> albumButton;

    public AlbumsPanel() {
        super();
        this.setLayout(new FlowLayout());
        this.setVisible(true);
        albumButton = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            JButton j = new JButton();
            albumButton.add(j);
            ImageIcon mg = new ImageIcon("D:\\AUT\\Term2\\JpotifyFinalProject\\images\\albumCover.jpg");
            albumButton.get(i).setIcon(mg);
            this.add(j);
            albumButton.get(i).setVisible(true);
        }
    }
}
