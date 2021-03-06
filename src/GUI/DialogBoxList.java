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

/**
 * This class is a dialog box containing a list
 * of the items saved in a file
 * @author Mahvash
 */
public class DialogBoxList extends JDialog {
    /**
     * It reads from the file and does the proper action based on the
     * listener
     * @param fileName the name of the file it reads from
     * @param listener the listener for selecting each item
     */
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
