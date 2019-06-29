package GUI;

import Controllers.ClickButtonRefreshFrinedActivity;
import Controllers.ClickListenerForFriendPlaylist;
import Logic.Main;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

/**
 * This class is the right panel of the main "JpotifyGUI" frame.
 * It shows the data received via network.
 * @author Mahvash
 */
public class FriendsActivityArea extends JPanel {
    private JScrollPane jScrollPane;
    private static boolean askedForLastListened = true;
    private static ArrayList<String> friendName;
    private static ArrayList<String> lastSong;
    private static ArrayList<String> lastTimeListened;
    private static ArrayList<Integer> ID;
    public FriendsActivityArea() {
        super();

        friendName =new ArrayList<>();
        lastSong=new ArrayList<>();
        lastTimeListened=new ArrayList<>();
        ID=new ArrayList<>();


        jScrollPane = new JScrollPane(this);
        jScrollPane.setViewportView(this);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setViewportBorder(new LineBorder(Color.pink));
        jScrollPane.updateUI();
        jScrollPane.setVisible(true);

        //if kooft<4 koodt=4 else kooft
        this.setLayout(new GridLayout(5,1));



        JButton refresh = new JButton(" Friends Activity ");
        refresh.setVisible(true);
        refresh.setPreferredSize(new Dimension(150,20));
        refresh.setFont(new Font("Verdana", 9, 10));
        refresh.addActionListener(new ClickButtonRefreshFrinedActivity());
        refresh.setBackground(Color.cyan);
        this.add(refresh);

        //this.add(new JSeparator());
        for (int i = 0; i <friendName.size(); i++) {

            JPanel friend=new JPanel();
            friend.setVisible(true);
            friend.setLayout(new GridLayout(4,1));
            this.add(friend);
            JLabel name=new JLabel(friendName.get(i));
            name.setFont(new Font("Verdana", 9, 10));
            name.setHorizontalTextPosition(0);
            name.setVerticalTextPosition(0);
            friend.add(name);

            JButton friendLastSong=new JButton();
            friendLastSong.setLayout(new GridLayout(1,2));
            JLabel songName=new JLabel(lastSong.get(i));
            JLabel timeListened=new JLabel(lastTimeListened.get(i));
            friendLastSong.add(songName);
            friendLastSong.add(timeListened);
            friendLastSong.setFont(new Font("Verdana", 9, 8));
            friendLastSong.setHorizontalTextPosition(0);
            friendLastSong.setVerticalTextPosition(0);
            friend.add(friendLastSong);


            JButton getPlaylist=new JButton("Play Their Playlist !");
            getPlaylist.setName(Main.getIP().get(i));
            getPlaylist.setBackground(Color.pink);
            getPlaylist.addActionListener(new ClickListenerForFriendPlaylist());
            friend.add(getPlaylist);
//            this.add(new JSeparator());
        }
    }

    public static void setID(ArrayList<Integer> ID) {
        FriendsActivityArea.ID = ID;
    }

    public static ArrayList<Integer> getID() {
        return ID;
    }


    public static void setFriendName(ArrayList<String> friendName) {
        FriendsActivityArea.friendName = friendName;
    }

    public static void setLastSong(ArrayList<String> lastSong) {
        FriendsActivityArea.lastSong = lastSong;
    }

    public static void setLastTimeListened(ArrayList<String> lastTimeListened) {
        FriendsActivityArea.lastTimeListened = lastTimeListened;
    }

    public static ArrayList<String> getFriendName() {
        return friendName;
    }

    public static ArrayList<String> getLastSong() {
        return lastSong;
    }

    public static ArrayList<String> getLastTimeListened() {
        return lastTimeListened;
    }


    public static boolean isAskedForLastListened() {
        return askedForLastListened;
    }

    public static void setAskedForLastListened(boolean askedForLastListened) {
        FriendsActivityArea.askedForLastListened = askedForLastListened;
    }

    @Override
    public void paint(Graphics g) {

        super.paint(g);

        Graphics2D g2 = (Graphics2D) g.create();
        int w = this.getWidth();
        int h = this.getHeight();
        //this line
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .5f));
        g2.setPaint(new GradientPaint(0, 0, Color.cyan, 0, h, Color.pink));
        g2.fillRect(0, 0, w, h);
        g2.dispose();
    }

    public JScrollPane getjScrollPane() {
        return jScrollPane;
    }

}
