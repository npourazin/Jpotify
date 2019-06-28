package Controllers;

import GUI.FriendsActivityArea;
import Logic.Main;
import Network.Client_ReceivesFiles;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ClickButtonRefreshFrinedActivity implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < Main.getIP().size(); i++) {
            connect(Main.getIP().get(i));
        }


    }
    public static void connect (String IP) {
        try {
            Client_ReceivesFiles client = new Client_ReceivesFiles(IP, 8080);
            FriendsActivityArea.getClientName().add(client.getYourName());
            FriendsActivityArea.getLastSong().add(client.getLastListenedTitle());
            FriendsActivityArea.getLastTimeListened().add(client.getLastListenedTime());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

