package GUI;
import Controllers.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;


class ChoicesArea extends JPanel{
    private JScrollPane jScrollPane ;

    ChoicesArea(){
        this.setLayout(new GridLayout(10, 1));

//        jScrollPane = new JScrollPane();
//        this.add(jScrollPane, BorderLayout.WEST);
        JScrollPane jScrollPane = new JScrollPane(this,   ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);


        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setViewportBorder(new LineBorder(Color.RED));
        jScrollPane.setVisible(true);


        JButton saveButton = new JButton("Add Song");
        saveButton.setFont(saveButton.getFont().deriveFont(14f)); // will only change size to 14pt

        saveButton.setVisible(true);
        this.add(saveButton);
        saveButton.addActionListener(new ClickListenerForAddingSongs());

        JButton songsButton = new JButton("Songs");
        songsButton.setVisible(true);
        songsButton.setFont(songsButton.getFont().deriveFont(14f)); // will only change size to 14pt

        this.add(songsButton);
        songsButton.addActionListener(new ClickListenerForAddingSongs());
        try {
            Image img = ImageIO.read(getClass().getResource("songs1.png"));
            songsButton.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        JButton albumsButton=new JButton("Albums");
        albumsButton.setVisible(true);
        albumsButton.setFont(albumsButton.getFont().deriveFont(14f)); // will only change size to 14pt
        this.add(albumsButton);
        albumsButton.addActionListener(new ClickListenerForShowingAlbums());

        JButton newPlayListButton=new JButton("New PlayList");
        newPlayListButton.setVisible(true);
        newPlayListButton.setFont(newPlayListButton.getFont().deriveFont(14f));
        this.add(newPlayListButton);
        newPlayListButton.addActionListener(new ClickListenerForNewPlayList());
        try {
            Image img = ImageIO.read(getClass().getResource("add1.png"));
            newPlayListButton.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            ex.printStackTrace();
        }


//        this.add(jScrollPane);
//        jScrollPane.setLayout(new BorderLayout());

        DefaultListModel model = new DefaultListModel();
        JList list = new JList(model);
//        jScrollPane.add(list);

    }

    public JScrollPane getjScrollPane() {
        return jScrollPane;
    }
}


