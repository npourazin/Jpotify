package Controllers;
import GUI.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClickListenerForShowingSongsList implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //TODO show that damned songs here and give each of them a button.
        ChoicesArea choicesArea = (ChoicesArea)actionEvent.getSource();
        choicesArea.validate();

    }
}
