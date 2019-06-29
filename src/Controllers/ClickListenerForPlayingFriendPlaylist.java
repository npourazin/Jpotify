package Controllers;

import Logic.Main;
import Logic.Music;
import Logic.PlayerManager;
import Logic.SongData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClickListenerForPlayingFriendPlaylist implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jB=(JButton)e.getSource();
        String ip = jB.getName();

        Main.setSongQueueIndex(0);
        PlayerManager.playerManager();
        //play the queue
        ButtonListenerPauseAndPlay.setSongToPlay();

    }
}
