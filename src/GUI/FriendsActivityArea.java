package GUI;

import Controllers.ClickListenerForFriendPlaylist;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public class FriendsActivityArea extends JPanel {
    private JScrollPane jScrollPane;
    private static boolean askedForLastListened = true;

    public FriendsActivityArea() {
        super();
        jScrollPane = new JScrollPane(this);
        jScrollPane.setViewportView(this);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setViewportBorder(new LineBorder(Color.pink));
        jScrollPane.updateUI();
        jScrollPane.setVisible(true);

        //if kooft<4 koodt=4 else kooft
        this.setLayout(new GridLayout(4,1));



        JLabel label = new JLabel(" Friends Activity ");
        label.setVisible(true);
        label.setFont(new Font("Verdana", 9, 17));
        label.setBackground(Color.cyan);
        this.add(label);
//        this.add(new JSeparator());

        for (int i = 0; i <2; i++) {

            JPanel friend=new JPanel();
            friend.setVisible(true);
            friend.setLayout(new GridLayout(4,1));
//            friend.setBackground(Color.cyan);
            this.add(friend);
            JLabel friendName=new JLabel("Mahvash");
            friendName.setFont(new Font("Verdana", 9, 10));
            friendName.setHorizontalTextPosition(0);
            friendName.setVerticalTextPosition(0);
            friend.add(friendName);

            JButton friendLastSong=new JButton();
            friendLastSong.setLayout(new GridLayout(1,2));
            JLabel songName=new JLabel("waves");
            JLabel timeListened=new JLabel("21:02");
            friendLastSong.add(songName);
            friendLastSong.add(timeListened);
            friendLastSong.setFont(new Font("Verdana", 9, 8));
            friendLastSong.setHorizontalTextPosition(0);
            friendLastSong.setVerticalTextPosition(0);
            friend.add(friendLastSong);


            JButton getPlaylist=new JButton("Play Their Playlist !");
            getPlaylist.setBackground(Color.pink);
            getPlaylist.addActionListener(new ClickListenerForFriendPlaylist());
            friend.add(getPlaylist);
//            this.add(new JSeparator());
        }
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
