package Controllers;
//import GUI.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PauseAndPlayClickButton implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jB = (JButton) (e.getSource());
        if (jB.getText().equals(" Play")) {
            jB.setText("Pause");
            try {
                Image img = ImageIO.read(getClass().getResource("pause1.png"));
                jB.setIcon(new ImageIcon(img));
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else {
            jB.setText(" Play");
            try {
                Image img = ImageIO.read(getClass().getResource("play1.png"));
                jB.setIcon(new ImageIcon(img));

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}