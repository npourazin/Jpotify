package GUI;

import Controllers.addButtonDialogListener;

import javax.swing.*;
import java.awt.*;

public class DialogBoxChooseName extends JDialog {
    private static JTextField textField;
    private JButton addButton;

    public DialogBoxChooseName(){
        JDialog addDialog=new JDialog();
        addDialog.setLayout(new GridLayout(1,2));
        addDialog.setSize(200, 100);
        addDialog.setBackground(Color.cyan);
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

    }
    public static JTextField getTextField() {
        return textField;
    }

}
