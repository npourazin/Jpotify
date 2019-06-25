package Controllers;

import GUI.DialogBoxList;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RemoveSongFromPlaylistItemClicked implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        DialogBoxList removeDialog=new DialogBoxList(SelectedPlaylistListener.getPlaylistName(),new SelectSongToRemoveFromPlaylist());
    }
}
