package com.example.guitest;

import com.system.login.SistemaLogin;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;

public class LoginController {
    @FXML
    private Label labelLoginMsg;
    @FXML
    private TextField tfUser;
    @FXML
    private PasswordField pfPass;
    @FXML
    private Button btnCloseWindow;

    public void LoginButtonAction(javafx.event.ActionEvent event) {
        if(!tfUser.getText().isBlank() && !pfPass.getText().isBlank()){
            SistemaLogin login = new SistemaLogin();

            if (login.credenciaisValidas(tfUser.getText(), pfPass.getText())){
                Main.changedScene("home");
            } else {
                labelLoginMsg.setText("Credenciais inválidas");
            }
        } else {
            labelLoginMsg.setText("Preencha os campos");
        }
    }
    public void CloseButtonAction(javafx.event.ActionEvent event){
        Stage stage = (Stage) btnCloseWindow.getScene().getWindow();
        stage.close();
    }

}