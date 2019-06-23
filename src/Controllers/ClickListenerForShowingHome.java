package Controllers;

import Logic.Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClickListenerForShowingHome implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        Main.getJpotifyGUI().getHomePanel().setVisible(true);
    }
}
