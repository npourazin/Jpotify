package Controllers;

import Logic.Main;
import Logic.PlayerManager;
import Logic.SavedSongData;
import javazoom.jl.player.Player;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;

public class JpotifyWindowActionListener implements WindowListener {
    @Override
    public void windowOpened(WindowEvent windowEvent) {
        Main.prepareObjIn();
        SavedSongData.readFromFile(Main.getObjIn());
    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {
        try {
            PrintWriter fr = new PrintWriter(new FileWriter("src/LastSongListened.txt"));
            if(!PauseAndPlayClickButton.getIfFirstTimePlaying()) {
                fr.println(PlayerManager.getsP().getFileName());
                fr.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        Main.prepareObjOut();
        SavedSongData.writeToFile(PlayerManager.getSongDataArrayList());
    }

    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowActivated(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }
}
