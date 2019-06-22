package Controllers;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClickListenerForNewPlayList implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame addFrame=new JFrame("New PlayList");
        addFrame.setSize(200, 100);
        addFrame.setVisible(true);
        JTextArea textArea=new JTextArea();
        textArea.setVisible(true);
        addFrame.add(textArea);
    }
}
