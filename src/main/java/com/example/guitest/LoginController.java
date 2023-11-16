package com.example.guitest;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class LoginController {
    @FXML
    protected void LoginAction(MouseEvent e) {
        Main.changedScene("home");
    }

}