package GUI;

import Controllers.PlaySpecificSongOnClick;
import Controllers.ShufflePlayButtonListener;
import Logic.Main;
import Logic.SongData;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class SongsPanel extends JPanel {
    private ArrayList<SongData> songs;
    private static ArrayList<JButton> songButton;
    private JScrollPane jScrollPane;

    public SongsPanel(ArrayList<SongData> songs) {
        super();

        //add scroller
        jScrollPane = new JScrollPane(this);
        jScrollPane.setViewportView(this);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setViewportBorder(new LineBorder(Color.pink));
        jScrollPane.updateUI();
        jScrollPane.setVisible(true);
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        this.songs = new ArrayList<>();
        this.songs = songs;
        System.out.println(songs.size());
        songButton = new ArrayList<>();
        this.setVisible(true);
        Dimension d = new Dimension(160, 160);
        System.out.println("song size:" + songs.size());

        //showing songs
        for (int i = 0; i < songs.size(); i++) {
            JButton j = new JButton();
            j.setPreferredSize(d);
            songButton.add(j);
            songButton.get(i).setLayout(new BorderLayout());
            this.add(songButton.get(i), gbc);
            songButton.get(i).setVisible(true);
            songButton.get(i).addActionListener(new PlaySpecificSongOnClick());
            if (songs.get(i).getIcon() != null)
                songButton.get(i).setIcon(new ImageIcon(((ImageIcon) songs.get(i).getIcon()).getImage().getScaledInstance(130, 130, Image.SCALE_DEFAULT)));
            if (songs.get(i).getSongName() != null) {
                JTextArea a = new JTextArea(songs.get(i).getSongName());
                songButton.get(i).add(a, BorderLayout.SOUTH);
            } else {
                JTextArea a = new JTextArea("UNKNOWN");
                songButton.get(i).add(a, BorderLayout.NORTH);

            }
            gbc.gridx++;
            if (gbc.gridx == 3) {
                gbc.gridx = 0;
                gbc.gridy++;
            }
            songButton.get(i).addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
//                super.mouseClicked(e);
                    if (SwingUtilities.isRightMouseButton(e)) {
                    }
                }
            });
            this.repaint();
            this.revalidate();

        }



    }

    public static ArrayList<JButton> getSongButton() {
        return songButton;
    }


}
