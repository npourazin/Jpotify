package Controllers;

import Logic.Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClickListenerForShowingHome implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        Main.getJpotifyGUI().getHomePanel().setVisible(true);
//        Main.getJpotifyGUI().getSongsPanel().setVisible(false);
//        Main.getJpotifyGUI().getAlbumsPanel().setVisible(false);
//        Main.getJpotifyGUI().getContentPane().remove(Main.getJpotifyGUI().getAlbumsPanel());
//        Main.getJpotifyGUI().getContentPane().remove(Main.getJpotifyGUI().getSongsPanel());
        Main.getJpotifyGUI().getHomePanel().removeAll();
        Main.getJpotifyGUI().getContentPane().invalidate();
        Main.getJpotifyGUI().getContentPane().revalidate();
        Main.getJpotifyGUI().getContentPane().repaint();
    }
}
