package GUI;

import javax.swing.*;
import javax.swing.plaf.LayerUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MusicPanel extends JPanel {
    MusicPanel(){
        super();
        this.setVisible(true);


//        this.createPanel();
//        this.setSize(800, 800);
//        this.add(new MusicSliderBar());
    }
    public void createPanel() {

        ButtonGroup entreeGroup = new ButtonGroup();
        JRadioButton radioButton;
        this.add(radioButton = new JRadioButton("what", true));
        entreeGroup.add(radioButton);
        this.add(radioButton = new JRadioButton("the"));
        entreeGroup.add(radioButton);
        this.add(radioButton = new JRadioButton("heeeeell"));
        entreeGroup.add(radioButton);

        this.add(new JCheckBox("kooft"));
        this.add(new JCheckBox("dard"));
        this.add(new JCheckBox("maraz"));

        this.add(new JLabel("Special requests:"));
        this.add(new JTextField(20));

        JButton orderButton = new JButton("Send Message");
        this.add(orderButton);

    }
}

//class MyActionListoner implements ActionListener {
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        System.out.println("boogh");
//    }
//}
