package Controllers;

import Logic.Main;
import Logic.PlayerManager;
import Logic.SongPlayer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShufflePlayButtonListener implements ActionListener {

    private static boolean shuffleOn=false;

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jB = (JButton) (e.getSource());
        if(jB.getText().equals("Shuffle Play Off")){
            shuffleOn=true;
//            Main.shufflePlayQueue();
            jB.setText("Shuffle Play On");
        }
        else if(jB.getText().equals("Shuffle Play On")){
            shuffleOn=false;
//            Main.shufflePlayQueue();
            jB.setText("Shuffle Play Off");
        }

    }
    public static boolean isShuffleOn() {
        return shuffleOn;
    }
}
