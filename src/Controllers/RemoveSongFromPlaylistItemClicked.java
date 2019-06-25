package Controllers;

import GUI.DialogBoxList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class RemoveSongFromPlaylistItemClicked implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        DialogBoxList removeDialog=new DialogBoxList(SelectedPlaylistListener.getPlaylistName(),new SelectSongToRemoveFromPlaylist());
    }
}
