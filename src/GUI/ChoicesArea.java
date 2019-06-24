package GUI;
import Controllers.*;
import Logic.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.*;
import java.util.Scanner;


public class ChoicesArea extends JPanel{
    private JScrollPane jScrollPane ;
    private static JLabel songIconLable;
    ChoicesArea(){
        this.setLayout(new GridLayout(10, 1));

//        jScrollPane = new JScrollPane();
//        this.add(jScrollPane, BorderLayout.WEST);
//         jScrollPane = new JScrollPane(this,   ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane = new JScrollPane(this);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setViewportBorder(new LineBorder(Color.pink));
//        jScrollPane.setBackground(Color.cyan);
        jScrollPane.updateUI();
        jScrollPane.setVisible(true);


        JButton saveButton = new JButton("Add Song");
        saveButton.setFont(saveButton.getFont().deriveFont(14f)); // will only change size to 14pt
//        saveButton.rep

        saveButton.setVisible(true);
        this.add(saveButton);
        saveButton.addActionListener(new ClickListenerForAddingSongs2());


        JButton homeButton=new JButton("Home");
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

        JLabel label=new JLabel("  Your Playlists:");
        label.setVisible(true);
        label.setFont(new Font("Verdana",9,13));
        label.setBackground(Color.cyan);
        this.add(label,BorderLayout.NORTH);

        DefaultListModel model = new DefaultListModel();
        JList list = new JList(model);
        list.setVisible(true);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);
        list.addListSelectionListener(new ListSelectionListener());
        this.add(list);
        try {
            String playlistName;
            if(!new File("src/PlaylistNames.txt").exists()) {
                PrintWriter fr = new PrintWriter(new FileWriter("src/PlaylistNames.txt"));
            }
            //Todo why new file?
            Scanner sc = new Scanner(new FileReader(new File("src/PlaylistNames.txt")));
            while (sc.hasNext()){
                playlistName=sc.nextLine();
                model.addElement(playlistName);
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
    }

        songIconLable = new JLabel();
        Dimension d=new Dimension(100,100);
        songIconLable.setVisible(true);
        songIconLable.setPreferredSize(d);
       this.add(songIconLable);
    }

    public static JLabel getSongIconLable(){
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

    public JScrollPane getjScrollPane() {
        return jScrollPane;
    }
}


