package Controllers;

import Logic.Main;
import Logic.SavedSongData;
import Network.FileComparator;
import Logic.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.*;

public class ClickListenerForFriendPlaylist implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jB=(JButton)e.getSource();
        int index=Integer.valueOf(jB.getName());
        ClickButtonRefreshFrinedActivity.connect(Main.getIPList().get(index));
        try {
            Main.getClient_receivesFiles().readMoreThanOneFiles();
            //TODO set jB's name correctly (must be the ip)
            String ip = jB.getName();


            //get the array list of songs in current queue

            File dir = new File("src/RECEIVED/"+ip+"/");
            List<File> files = Arrays.asList(dir.listFiles());
//
            Main.setCurrentQueue(null);//WHY?
            ArrayList<SongData> tempArr=new ArrayList<>();
            for (File file : files) {
//                System.out.println(file.getName() + "\t" + new Date(file.lastModified()));
                Music music=new Music(file.getAbsolutePath());
                tempArr.add(music.getSongData());
            }
//
//
            Main.setCurrentQueue(tempArr);
            Main.setSongQueueIndex(0);
            PlayerManager.playerManager();


        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}
