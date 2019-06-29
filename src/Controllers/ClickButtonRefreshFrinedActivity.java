package Controllers;

import GUI.FriendsActivityArea;
import Logic.Main;
import Network.Client_ReceivesFiles;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ClickButtonRefreshFrinedActivity implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {

        //empty everything
        FriendsActivityArea.setFriendName(null);
        FriendsActivityArea.setLastSong(null);
        FriendsActivityArea.setLastTimeListened(null);


        for (int i = 0; i < Main.getIP().size(); i++) {
            connect(Main.getIP().get(i));
        }

        int rows=FriendsActivityArea.getFriendName().size();
        if(rows<5) rows=5;
        Main.getJpotifyGUI().getFriendsActivityArea().setLayout(new GridLayout(rows,1));

    }

    public static void connect (String IP) {
        try {
            Main.setClient_receivesFiles( new Client_ReceivesFiles(IP, 8080));
            if(Main.getClient_receivesFiles()==null) return;
            FriendsActivityArea.getFriendName().add(Main.getClient_receivesFiles().getYourName());
            FriendsActivityArea.getLastSong().add(Main.getClient_receivesFiles().getLastListenedTitle());
            FriendsActivityArea.getLastTimeListened().add(Main.getClient_receivesFiles().getLastListenedTime());
            FriendsActivityArea.getID().add(Main.getClient_receivesFiles().getID());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

