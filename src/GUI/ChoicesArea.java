package GUI;

import Controllers.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.Scanner;


public class ChoicesArea extends JPanel {
    private JScrollPane jScrollPane;
    private static DefaultListModel model;
    //TODO: make not static:
    private static JLabel songIconLable;

    public ChoicesArea() {
        this.setLayout(new GridLayout(10, 1));

//        jScrollPane = new JScrollPane();
//        this.add(jScrollPane, BorderLayout.WEST);
//         jScrollPane = new JScrollPane(this,   ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);


        JButton saveButton = new JButton("Add Song");
        saveButton.setFont(saveButton.getFont().deriveFont(14f)); // will only change size to 14pt
//        saveButton.rep

        saveButton.setVisible(true);
        this.add(saveButton);
        saveButton.addActionListener(new ClickListenerForAddingSongs2());


        JButton homeButton = new JButton("Home");
        homeButton.setVisible(true);
        homeButton.setFont(homeButton.getFont().deriveFont(14f));
        this.add(homeButton);
        homeButton.addActionListener(new ClickListenerForShowingHome());

        JButton songsButton = new JButton("Songs");
        songsButton.setVisible(true);
        songsButton.setFont(songsButton.getFont().deriveFont(14f)); // will only change size to 14pt

        this.add(songsButton);
        songsButton.addActionListener(new ClickListenerForShowingSongsList());
        try {
            Image img = ImageIO.read(getClass().getResource("songs1.png"));
            songsButton.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        JButton albumsButton = new JButton("Albums");
        albumsButton.setVisible(true);
        albumsButton.setFont(albumsButton.getFont().deriveFont(14f)); // will only change size to 14pt
        this.add(albumsButton);
        albumsButton.addActionListener(new ClickListenerForShowingAlbums());

        JButton newPlayListButton = new JButton("New PlayList");
        newPlayListButton.setVisible(true);
        newPlayListButton.setFont(newPlayListButton.getFont().deriveFont(14f));
        this.add(newPlayListButton);
        newPlayListButton.addActionListener(new ClickListenerForNewPlaylist());
        try {
            Image img = ImageIO.read(getClass().getResource("add1.png"));
            newPlayListButton.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        JLabel playlistLable = new JLabel("  Your Playlists:");
        playlistLable.setVisible(true);
        playlistLable.setFont(new Font("Verdana", 9, 13));
        playlistLable.setBackground(Color.cyan);
        this.add(playlistLable, BorderLayout.NORTH);

        model = new DefaultListModel();
        JList list = new JList(model);
        list.setVisible(true);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);
        list.addListSelectionListener(new SelectedPlaylistListener());
//        list.addListSelectionListener(new ListSelectionListener());
        try {
            String playlistName;
            if (!new File("src/PlaylistNames.txt").exists()) {
                PrintWriter fr = new PrintWriter(new FileWriter("src/PlaylistNames.txt"));
            }
            //Todo why new file?
            Scanner sc = new Scanner(new FileReader(new File("src/PlaylistNames.txt")));
            while (sc.hasNext()) {
                playlistName = sc.nextLine();
                model.addElement(playlistName);
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        //Menu for right click on the list
        final JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem showPlaylist=new JMenuItem("Show Playlist");
        showPlaylist.addActionListener(new ShowPlaylistMenuItemClicked());
        popupMenu.add(showPlaylist);
        popupMenu.add(new JPopupMenu.Separator());
        JMenuItem addSong =new JMenuItem("Add Songs");
        addSong.addActionListener(new AddSongToPlaylistItemClicked());
        popupMenu.add(addSong);
        JMenuItem removeSong=new JMenuItem("Remove Songs");
        removeSong.addActionListener(new RemoveSongFromPlaylistItemClicked());
        popupMenu.add(removeSong);

//        list.addMouseListener(new RightClickListener());
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                super.mouseClicked(e);
                if (SwingUtilities.isRightMouseButton(e)    // if right mouse button clicked
                        && !list.isSelectionEmpty()            // and list selection is not empty
                        && list.locationToIndex(e.getPoint()) // and clicked point is
                        == list.getSelectedIndex()) {       //   inside selected item bounds
                    popupMenu.show(list, e.getX(), e.getY());
                }
            }
        });

        jScrollPane = new JScrollPane();
        jScrollPane.setViewportView(list);
        this.add(jScrollPane);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setViewportBorder(new LineBorder(Color.pink));
        jScrollPane.updateUI();
        jScrollPane.setVisible(true);


        JLabel currentSongLable = new JLabel("  Current Song:");
        currentSongLable.setVisible(true);
        currentSongLable.setFont(new Font("Verdana", 9, 13));
        currentSongLable.setBackground(Color.cyan);
        this.add(currentSongLable, BorderLayout.NORTH);

        //creating song icon
//        JPanel bottomPanel = new JPanel();
        songIconLable = new JLabel();
        Dimension d = new Dimension(200, 800);
        songIconLable.setVisible(true);
        songIconLable.setPreferredSize(d);
        this.add(songIconLable,BorderLayout.SOUTH);
//        bottomPanel.add(songIconLable);
//        this.add(bottomPanel,SpringLayout.SOUTH);
    }
    public static DefaultListModel getModel(){
        return model;
    }

    public static JLabel getSongIconLable() {
        return songIconLable;
    }

    @Override
    public void paint(Graphics g) {

        super.paint(g);

        Graphics2D g2 = (Graphics2D) g.create();
        int w = this.getWidth();
        int h = this.getHeight();
        //this line
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .5f));
        g2.setPaint(new GradientPaint(0, 0, Color.pink, 0, h, Color.cyan));
        g2.fillRect(0, 0, w, h);
        g2.dispose();
    }

}


