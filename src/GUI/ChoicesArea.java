package GUI;
import Controllers.*;

import javax.swing.*;
import java.awt.*;


class ChoicesArea extends JPanel{

    ChoicesArea(){
        this.setLayout(new FlowLayout());
        JButton saveButton = new JButton("Add Song");
        saveButton.setVisible(true);
        this.add(saveButton);
        saveButton.addActionListener(new ClickListenerForAddingSongs());

        JButton songsButton = new JButton("Songs");
        songsButton.setVisible(true);
        this.add(songsButton);
    }

}


