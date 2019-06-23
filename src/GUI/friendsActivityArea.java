package GUI;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class friendsActivityArea extends JPanel {
    private JScrollPane jScrollPane;
    public friendsActivityArea(){
        super();
        this.setLayout(new BorderLayout());
         jScrollPane= new JScrollPane(this);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setViewportBorder(new LineBorder(Color.pink));
        jScrollPane.setBackground(Color.cyan);
        jScrollPane.updateUI();
        jScrollPane.setVisible(true);

//        this.setBackground(Color.cyan);
        JLabel label=new JLabel(" Friends Activity ");
        label.setVisible(true);
        label.setFont(new Font("Verdana",9,18));
        label.setBackground(Color.cyan);
        this.add(label,BorderLayout.NORTH);
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
