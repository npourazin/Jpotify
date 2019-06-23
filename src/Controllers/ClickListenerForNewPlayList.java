package Controllers;

import GUI.MyLayerUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClickListenerForNewPlayList implements ActionListener {
    private static boolean visible=false;
    @Override
    public void actionPerformed(ActionEvent e) {
        visible=true;
        JDialog addDialog=new JDialog();
        addDialog.setLayout(new GridLayout(1,2));
        addDialog.setSize(200, 100);
        addDialog.setBackground(Color.pink);
        addDialog.setVisible(true);
        addDialog.getDefaultCloseOperation();
        JTextField textField=new JTextField();
        textField.setVisible(true);
        addDialog.add(textField);
        textField.setBackground(Color.cyan);
        JButton addButton=new JButton("Add");
        addButton.setBackground(Color.pink);
        addButton.setVisible(true);
        addDialog.add(addButton);

    }

}
