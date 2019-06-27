package Controllers;

import GUI.DialogBoxChooseName;
import GUI.MyLayerUI;
import Logic.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.*;
import java.util.Scanner;

public class ClickListenerForNewPlaylist implements ActionListener {
    private static boolean visible=false;
    @Override
    public void actionPerformed(ActionEvent e) {
        visible=true;
        new DialogBoxChooseName("ADD",new addButtonDialogListener());

    }

}
