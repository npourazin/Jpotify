package GUI;

import Controllers.ShowSpecificAlbumOnClick;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AlbumsPanel extends JPanel {

    private ArrayList<JButton> albumButton;
    private static ArrayList<String> albums;
    private JScrollPane jScrollPane;

    public AlbumsPanel() {
        super();
        this.setLayout(new FlowLayout());
        this.setVisible(true);
        albumButton = new ArrayList<>();
        albums=new ArrayList<>();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        try {
            Scanner sc = new Scanner(new FileReader(new File("src/AllAlbums.txt")));
            while (sc.hasNext()) {
                albums.add(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //TODO: give each button a listener to play the song
        Dimension d = new Dimension(160, 160);
        for (int i = 0; i < albums.size(); i++) {
            JButton j = new JButton();
            j.setPreferredSize(d);
            albumButton.add(j);
            albumButton.get(i).addActionListener(new ShowSpecificAlbumOnClick());
            albumButton.get(i).setLayout(new BorderLayout());
            this.add(albumButton.get(i), gbc);
            albumButton.get(i).setVisible(true);
            albumButton.get(i).setName(albums.get(i));

            try {

                Image img = ImageIO.read(getClass().getResource("defaultSongIcon.png"));
                albumButton.get(i).setIcon(new ImageIcon(img.getScaledInstance(130,130,Image.SCALE_DEFAULT)));
            } catch (IOException e) {
                e.printStackTrace();
            }

            JTextArea a = new JTextArea(albums.get(i));
            albumButton.get(i).add(a, BorderLayout.SOUTH);

            gbc.gridx++;
            if (gbc.gridx == 3) {
                gbc.gridx = 0;
                gbc.gridy++;
            }
            this.repaint();
            this.revalidate();
        }
    }

    public JScrollPane getjScrollPane() {
        return jScrollPane;
    }

}
