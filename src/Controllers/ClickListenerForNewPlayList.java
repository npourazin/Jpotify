package Controllers;

import GUI.MyLayerUI;
import Logic.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.*;
import java.util.Scanner;

public class ClickListenerForNewPlayList implements ActionListener {
    private static boolean visible=false;
    private static JTextField textField;
    private JButton addButton;
    @Override
    public void actionPerformed(ActionEvent e) {
        visible=true;
        JDialog addDialog=new JDialog();
        addDialog.setLayout(new GridLayout(1,2));
        addDialog.setSize(200, 100);
        addDialog.setBackground(Color.pink);
        addDialog.setVisible(true);
        addDialog.getDefaultCloseOperation();
        textField=new JTextField();
        textField.setVisible(true);
        addDialog.add(textField);
        textField.setBackground(Color.cyan);
        addButton=new JButton("Add");
        addButton.setBackground(Color.pink);
        addButton.setVisible(true);
        addButton.addActionListener(new addButtonDialogListener());
        addDialog.add(addButton);

        try {
            Scanner sc = new Scanner(new FileReader(new File("src/PlaylistNames.txt")));
            PrintWriter fr = new PrintWriter(new FileWriter(new File("src/PlaylistNames.txt"), true));

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }



    }

    public static JTextField getTextField() {
        return textField;
    }
}
