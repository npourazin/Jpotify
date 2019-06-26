package Controllers;

import Logic.Main;
import Logic.PlayerManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NextButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        int newIndex=Main.getSongQueueIndex()+1;
        if( newIndex>=Main.getCurrentQueue().size()) newIndex=0;
        System.out.println(newIndex);
        Main.setSongQueueIndex(newIndex);
        PlayerManager.playerManager();
    }
}
