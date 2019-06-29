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



        try {
            Main.getClient_receivesFiles().readAFile();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        String songName=null;
        for (int i = 0; i <ClickButtonRefreshFrinedActivity.getReceivedFriendInfos().size() ; i++) {
            if(ClickButtonRefreshFrinedActivity.getReceivedFriendInfos().get(i).getIP().equals(ip)){
                songName=ClickButtonRefreshFrinedActivity.getReceivedFriendInfos().get(i).getLastSong();
                break;
            }
        }


        int queueIndex=0;
        File dir = new File("src/RECEIVED/"+ip+"/");
        List<File> files = Arrays.asList(dir.listFiles());
        Main.setCurrentQueue(null);//WHY?
        ArrayList<SongData> tempArr=new ArrayList<>();
        for (File file : files) {
            Music music=new Music(file.getAbsolutePath());
            tempArr.add(music.getSongData());
        }

        QueueIndexController.setIndex(queueIndex);
        PlayerManager.playerManager();
        ButtonListenerPauseAndPlay.setSongToPlay();
        //play the queue
        ButtonListenerPauseAndPlay.setSongToPlay();
    }
}
