package Controllers;

import Logic.Main;
import Logic.Music;
import Logic.PlayerManager;
import Logic.SongData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClickListenerForPlayingTheLastSongFriendPlaylist implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jB=(JButton)e.getSource();
        String ip = jB.getName();


        ClickButtonRefreshFrinedActivity.connect(ip);

        try {
            Main.getClient_receivesFiles().readAFile();
        } catch (IOException ex) {
            ex.printStackTrace();
        }



        File dir = new File("src/RECEIVED/"+ip+"/");
        List<File> files = Arrays.asList(dir.listFiles());
        Main.setCurrentQueue(null);//WHY?
        ArrayList<SongData> tempArr=new ArrayList<>();
        for (File file : files) {
            Music music=new Music(file.getAbsolutePath());
            tempArr.add(music.getSongData());
        }

        Main.setCurrentQueue(tempArr);
        Main.setSongQueueIndex(0);
        PlayerManager.playerManager();
        //play the queue
        ButtonListenerPauseAndPlay.setSongToPlay();
    }
}
