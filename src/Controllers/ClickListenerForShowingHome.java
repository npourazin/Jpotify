package Controllers;

import Logic.Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClickListenerForShowingHome implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        Main.getJpotifyGUI().getHomePanel().setVisible(true);
        Main.getJpotifyGUI().getSongsPanel().setVisible(false);
        Main.getJpotifyGUI().getAlbumsPanel().setVisible(false);
        Main.getJpotifyGUI().remove(Main.getJpotifyGUI().getAlbumsPanel());
        Main.getJpotifyGUI().remove(Main.getJpotifyGUI().getSongsPanel());

        Main.getJpotifyGUI().revalidate();
        Main.getJpotifyGUI().repaint();
    }
}
