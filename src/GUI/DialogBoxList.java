package GUI;

import Controllers.SelectSongToAddToPlaylistListener;
import Controllers.SelectedPlaylistListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import javax.swing.event.ListSelectionEvent;


public class DialogBoxList extends JDialog {
    public DialogBoxList(String fileName, javax.swing.event.ListSelectionListener listener){
        super();
        this.setLayout(new GridLayout(1, 2));
        this.setSize(200, 400);
        this.setBackground(Color.pink);
        this.setVisible(true);
        this.getDefaultCloseOperation();
        DefaultListModel model = new DefaultListModel();
        JList list = new JList(model);
        list.setBackground(Color.pink);
        list.setVisible(true);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);
        list.addListSelectionListener(listener);
        this.add(list);
        String playlistName;
        Scanner sc = null;
        try {
            sc = new Scanner(new FileReader(new File("src/"+ fileName+".txt")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (sc.hasNext()) {
            playlistName = sc.nextLine();
            model.addElement(playlistName);
        }
    }
}
