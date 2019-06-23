package Controllers;

import GUI.*;
import Logic.Main;
import Logic.PlayerManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClickListenerForShowingSongsList implements ActionListener {
    private static boolean visible = false;

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //TODO show that damned songs here and give each of them a button.
//        ChoicesArea choicesArea = (ChoicesArea)actionEvent.getSource();
//        choicesArea.validate();
        SongsPanel songsPanel = new SongsPanel(PlayerManager.getSongDataArrayList());
        songsPanel.setVisible(true);
        Main.getJpotifyGUI().getHomePanel().setVisible(false);
        Main.getJpotifyGUI().add(songsPanel, BorderLayout.CENTER);

        JLayer<Component> jLayer;
        jLayer = new JLayer<>(songsPanel, new MyLayerUI());
        Main.getJpotifyGUI().add(jLayer, BorderLayout.CENTER);
        Main.getJpotifyGUI().validate();
        System.out.println("A");
        setVisible();
        HomePanel.setPanelNotVisible();
    }

    public static boolean ifVisible() {
        return visible;
    }

    public static void setVisible() {
        visible = true;
    }

    public static void setNotVisible() {
        visible = false;
    }
}
