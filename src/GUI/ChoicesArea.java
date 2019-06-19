package GUI;
import Controllers.*;

import javax.swing.*;
import java.awt.*;


class ChoicesArea extends JPanel{
    DefaultListModel model = new DefaultListModel();
    ChoicesArea(){
        this.setLayout(new GridLayout(10, 1));
        JButton saveButton = new JButton("Add Song");
        saveButton.setVisible(true);
        this.add(saveButton);
        saveButton.addActionListener(new ClickListenerForAddingSongs());
//        model.add(0, saveButton);

        JButton songsButton = new JButton("Songs");
        songsButton.setVisible(true);
        this.add(songsButton);
//        model.add(1, songsButton);
        //action listener for this

//        Object[] items = new JButton[] {
//               saveButton, songsButton,
//        };
//        this.setVisible(true);
//        JList list = new JList(items);
//        list.setCellRenderer(new ButtonListRenderer());
//        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        list.setVisibleRowCount(5);
//        list.setVisible(true);
//        this.setLayout(new BorderLayout());
//        this.add(list , BorderLayout.WEST);
//        this.add(list);
    }

}


