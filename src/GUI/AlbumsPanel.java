package GUI;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public class AlbumsPanel extends JPanel {

    private ArrayList<JButton> albumButton;
private JScrollPane jScrollPane;
    public AlbumsPanel() {
        super();
        this.setLayout(new FlowLayout());
        this.setVisible(true);
        jScrollPane = new JScrollPane(this);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setViewportBorder(new LineBorder(Color.pink));
        jScrollPane.setBackground(Color.cyan);
        jScrollPane.updateUI();
        jScrollPane.setVisible(true);


        albumButton = new ArrayList<>();

        //TODO: compelete the following code.

        //TODO: give each button a listener to play the song

        for (int i = 0; i < 6; i++) {
            JButton j = new JButton();
            albumButton.add(j);
            ImageIcon mg = new ImageIcon("D:\\AUT\\Term2\\JpotifyFinalProject\\images\\albumCover.jpg");
            albumButton.get(i).setIcon(mg);
            this.add(j);
            albumButton.get(i).setVisible(true);
        }
    }
    public JScrollPane getjScrollPane() {
        return jScrollPane;
    }

}
