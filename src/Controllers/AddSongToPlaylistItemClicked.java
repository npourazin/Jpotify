package Controllers;

import GUI.DialogBoxList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class AddSongToPlaylistItemClicked implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {

        DialogBoxList aggDialog=new DialogBoxList("AddedSongAdresses",new SelectSongToAddToPlaylistListener());

//        try {
//            JFrame jFrame = new JFrame("All Songs");
//            jFrame.setVisible(true);
//            jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
//            JDialog addDialog = new JDialog();
//            addDialog.setLayout(new GridLayout(1, 2));
//            addDialog.setSize(200, 100);
//            addDialog.setBackground(Color.pink);
//            addDialog.setVisible(true);
//            addDialog.getDefaultCloseOperation();
//            DefaultListModel model = new DefaultListModel();
//            JList list = new JList(model);
//            list.setVisible(true);
//            list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//            list.setLayoutOrientation(JList.VERTICAL);
//            list.setVisibleRowCount(-1);
//            list.addListSelectionListener(new SelectSongToAddToPlaylistListener());
//            addDialog.add(list);
//            try {
//
//                //Showing the list of the songs paths
//                String playlistName;
//                Scanner sc = new Scanner(new FileReader(new File("src/AddedSongAdresses.txt")));
//                while (sc.hasNext()) {
//                    playlistName = sc.nextLine();
//                    model.addElement(playlistName);
//                }
//
//            } catch (FileNotFoundException ex) {
//                ex.printStackTrace();
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        } finally {
//
//        }

    }
}
