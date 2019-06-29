package GUI;

import Controllers.*;

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
    private static JButton refresh;
    public FriendsActivityArea() {
        super();
        ClickButtonRefreshFrinedActivity.setReceivedFriendInfos(new ArrayList< ReceivedFriendInfo>());

        jScrollPane = new JScrollPane(this);
        jScrollPane.setViewportView(this);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setViewportBorder(new LineBorder(Color.pink));
        jScrollPane.updateUI();
        jScrollPane.setVisible(true);

        int rows=ClickButtonRefreshFrinedActivity.getReceivedFriendInfos().size();
        if(rows<5) rows=5;
        this.setLayout(new GridLayout(rows,1));


        refresh = new JButton(" Friends Activity ");
        refresh.setVisible(true);
        refresh.setPreferredSize(new Dimension(190,20));
        refresh.setFont(new Font("Verdana", 9, 10));
        refresh.addActionListener(new ClickButtonRefreshFrinedActivity());
        refresh.setBackground(Color.cyan);
        this.add(refresh);



    }

    public static JButton getRefresh() {
        return refresh;
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
