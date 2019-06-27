package Controllers;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RepeatButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jB = (JButton) (e.getSource());
        if(jB.getText().equals("Off")){
            //TODO do smth
            jB.setText("On");
        }
        else if(jB.getText().equals("On")){
            //TODO do smth
            jB.setText("Off");
        }

    }
}
