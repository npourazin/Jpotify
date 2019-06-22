package Controllers;

import Logic.PlayerManager;
import Logic.SongPlayer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StopButtonListener implements ActionListener {
    private static SongPlayer sP;

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jB = (JButton) (e.getSource());
        sP= PlayerManager.getsP();
        sP.stopSong();
    }
}
