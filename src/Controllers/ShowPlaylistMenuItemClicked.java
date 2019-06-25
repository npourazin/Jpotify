package Controllers;

import Logic.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowPlaylistMenuItemClicked implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
//        JMenuItem mI = (JMenuItem) (e.getSource());
        System.out.println(SelectedPlaylistListener.getPlaylistName());
        System.out.println("clicked");
        Main.creatCurrentQueue("src/"+SelectedPlaylistListener.getPlaylistName()+".txt");
    }
}
