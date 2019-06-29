package Controllers;

import Logic.Main;
import Logic.SavedSongData;
import Network.FileComparator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ClickListenerForFriendPlaylist implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jB=(JButton)e.getSource();
        int index=Integer.valueOf(jB.getName());
        ClickButtonRefreshFrinedActivity.connect(Main.getIP().get(index));
        try {
            Main.getClient_receivesFiles().readMoreThanOneFiles();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}
