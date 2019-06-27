package GUI;

import Controllers.ClickListenerForSearchButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SearcBarPanel extends JPanel {
    public SearcBarPanel(){
        this.setVisible(true);
        this.setLayout(new FlowLayout());

        JTextArea name = new JTextArea("Search: ");
        name.setEditable(false);
        name.setVisible(true);
        name.setBackground(Color.cyan);
        this.add(name);

        JTextField searched = new JTextField("");
        searched.setEditable(true);
        searched.setVisible(true);
        searched.setBackground(Color.white);
        this.setBorder(new EmptyBorder(2, 2, 2, 2));
        this.add(searched);

        JButton confirmSearch = new JButton("Search!");
        confirmSearch.setVisible(true);
        confirmSearch.addActionListener(new ClickListenerForSearchButton());

    }
}
