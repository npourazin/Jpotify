package Controllers;

import GUI.SignInOrSignUp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClickListenerForAccountManager implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        SignInOrSignUp.getFrames()[0].dispose();
    }
}

