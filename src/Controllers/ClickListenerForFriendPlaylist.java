package Controllers;

import Logic.Main;
import Logic.SavedSongData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ClickListenerForFriendPlaylist implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jB=(JButton)e.getSource();
        int index=Integer.valueOf(jB.getName());
        ClickButtonRefreshFrinedActivity.connect(Main.getIP().get(index));
        try {
            Main.getClient_receivesFiles().readMoreThanOneFiles();

            //get the array list of songs in current queue
            Main.prepareObjIn();


        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}
