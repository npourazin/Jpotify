package GUI;

import Controllers.JpotifyWindowActionListener;
import Logic.Main;
import Logic.PlayerManager;
import Logic.SongPlayer;

import javax.swing.*;
import java.awt.*;

public class JpotifyGUI extends JFrame {
    private static HomePanel homePanel;
    private static SongsPanel songsPanel;
    private static MusicSliderBar musicSliderBar;
    private static JLayer<Component> jLayer;
    private static AlbumsPanel albumsPanel;


    public static void setSongsPanel(SongsPanel songsPanel) {
        JpotifyGUI.songsPanel = songsPanel;
    }

    public void setAlbumsPanel(AlbumsPanel albumsPanel) {
        JpotifyGUI.albumsPanel = albumsPanel;
    }

    public static SongsPanel getSongsPanel() {
        return songsPanel;
    }

    public static AlbumsPanel getAlbumsPanel() {
        return albumsPanel;
    }


    public static ChoicesArea getChoicesArea() {
        return choicesArea;
    }

    private static ChoicesArea choicesArea;

    public JpotifyGUI() {
        super();

        this.addWindowListener(new JpotifyWindowActionListener());

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        this.setTitle("Jpotify");
        this.setVisible(true);
        this.setSize(1000, 600);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

//        JScrollPane jScrollPane = new JScrollPane(this,   ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

//        jScrollPane.setPreferredSize(new Dimension(600, 600));
        choicesArea = new ChoicesArea();
        choicesArea.setVisible(true);
        this.add(choicesArea, BorderLayout.WEST);


        friendsActivityArea friendsActivityArea = new friendsActivityArea();
        friendsActivityArea.setVisible(true);
        this.add(friendsActivityArea, BorderLayout.EAST);


//        if(HomePanel.ifVisible()) {
        homePanel = new HomePanel();
        this.add(homePanel.getjScrollPane(), BorderLayout.CENTER);
        jLayer = new JLayer<>(homePanel.getjScrollPane(), new MyLayerUI());
        this.add(jLayer, BorderLayout.CENTER);
//        }

//        else if(ClickListenerForShowingSongsList.ifVisible()) {
//            SongsPanel songsPanel = new SongsPanel(PlayerManager.getSongDataArrayList());
//            songsPanel.setVisible(true);
//            this.add(songsPanel, BorderLayout.CENTER);
//            jLayer = new JLayer<>(songsPanel, new MyLayerUI());
//            this.add(jLayer, BorderLayout.CENTER);
//        }
        //time is to be given here it is given 100 as default
        musicSliderBar = new MusicSliderBar(100);
        musicSliderBar.setVisible(true);
        this.add(musicSliderBar, BorderLayout.SOUTH);

//        choicesArea.paint();
        choicesArea.setBackground(Color.cyan);
//        JLayer<Component> jLayer1 = new JLayer<>(choicesArea, new MyLayerUI());
//        this.add(jLayer1, BorderLayout.WEST);

        //refreshes the layout after changes
        this.validate();
        //  this.pack();
    }

    public static JLayer<Component> getjLayer(){
        return jLayer;
    }
    public HomePanel getHomePanel() {
        return homePanel;
    }

    public static MusicSliderBar getMusicSliderBar() {
        return musicSliderBar;
    }
}
