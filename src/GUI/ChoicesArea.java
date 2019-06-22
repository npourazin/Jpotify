package GUI;
import Controllers.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;


class ChoicesArea extends JPanel{

    ChoicesArea(){
        this.setLayout(new GridLayout(10, 1));

        JButton saveButton = new JButton("Add Song");
        saveButton.setVisible(true);
        this.add(saveButton);
        saveButton.addActionListener(new ClickListenerForAddingSongs());

        JButton songsButton = new JButton("Songs");
        songsButton.setVisible(true);
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
        this.add(albumsButton);
        albumsButton.addActionListener(new ClickListenerForShowingAlbums());

        JButton newPlayListButton=new JButton("New PlayList");
        newPlayListButton.setVisible(true);
        this.add(newPlayListButton);
        newPlayListButton.addActionListener(new ClickListenerForNewPlayList());
        try {
            Image img = ImageIO.read(getClass().getResource("add1.png"));
            newPlayListButton.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

//        ScrollPane scrollPane=new ScrollPane();
//        scrollPane.setVisible(true);
//        this.add(scrollPane);
//        scrollPane.setLayout(new BorderLayout());
        DefaultListModel model = new DefaultListModel();
        JList list = new JList(model);
        this.add(list);

    }

}


