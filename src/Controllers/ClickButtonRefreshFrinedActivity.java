package Controllers;

import GUI.FriendsActivityArea;
import Logic.Main;
import Network.Client_ReceivesFiles;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class ClickButtonRefreshFrinedActivity implements ActionListener {

    public static void setReceivedFriendInfos(ArrayList<ReceivedFriendInfo> receivedFriendInfos) {
        ClickButtonRefreshFrinedActivity.receivedFriendInfos = receivedFriendInfos;
    }

    public static ArrayList<ReceivedFriendInfo> getReceivedFriendInfos() {
        return receivedFriendInfos;
    }

    private static ArrayList<ReceivedFriendInfo> receivedFriendInfos;
    @Override
    public void actionPerformed(ActionEvent e) {

        //empty everything
       receivedFriendInfos=new ArrayList<>();


        for (int i = 0; i < Main.getIPList().size(); i++) {
            connect(Main.getIPList().get(i));
        }

    }

    public static void connect (String IP) {
        try {
            Main.setClient_receivesFiles( new Client_ReceivesFiles(IP, 8080));
            if(Main.getClient_receivesFiles()==null) return;
            ClickButtonRefreshFrinedActivity.receivedFriendInfos.add(new ReceivedFriendInfo(Client_ReceivesFiles.getYourName(),Main.getClient_receivesFiles().getLastListenedTitle(),Main.getClient_receivesFiles().getLastListenedTime(), IP));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

