package Controllers;

import Logic.Main;
import Logic.PlayerManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PreviousButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        int newIndex= Main.getSongQueueIndex()-1;
        if(newIndex<0 || newIndex>=Main.getCurrentQueue().size()) newIndex=Main.getCurrentQueue().size()-1;
        Main.setSongQueueIndex(newIndex);
        PlayerManager.PlayerManager();
    }
}
