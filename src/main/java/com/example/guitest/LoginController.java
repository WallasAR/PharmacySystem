package com.example.guitest;

import com.system.login.SistemaLogin;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class LoginController implements Initializable{
    @FXML
    private Label labelLoginMsg;
    @FXML
    private TextField tfUser;
    @FXML
    private PasswordField pfPass;
    @FXML
    private Pane btnCloseWindow;
    @FXML
    private Pane btnMinimizeWindow;

    public void LoginButtonAction(javafx.event.ActionEvent event) {
        if (!tfUser.getText().isBlank() && !pfPass.getText().isBlank()) {
            SistemaLogin login = new SistemaLogin();

            if (SistemaLogin.credenciaisValidas(tfUser.getText(), pfPass.getText())) {
                Main.changedScene("home");
            } else if (SistemaLogin.credenciaisValidasFunc(tfUser.getText(), pfPass.getText())) {
                Main.changedScene("sale");
            } else {
                labelLoginMsg.setText("Credenciais inválidas");
            }
        } else {
            labelLoginMsg.setText("Preencha os campos");
        }
    }

    public void CloseButtonAction(MouseEvent event) {
        Stage stage = (Stage) btnCloseWindow.getScene().getWindow();
        stage.close();
    }

    public void MinimizeClicked(MouseEvent event) {
        Stage stage = (Stage) btnMinimizeWindow.getScene().getWindow(); // Atribui a variavel a capacidade de minimar a tela
        stage.setIconified(true);
    }

    public void LoginButtonKeyAction(KeyEvent event) {
        if (event.getCode() == event.getCode().ENTER) {
            if (!tfUser.getText().isBlank() && !pfPass.getText().isBlank()) {

                if (SistemaLogin.credenciaisValidas(tfUser.getText(), pfPass.getText())) {
                    Main.changedScene("home");
                } else if (SistemaLogin.credenciaisValidasFunc(tfUser.getText(), pfPass.getText())) {
                    Main.changedScene("sale");
                } else {
                    labelLoginMsg.setText("Credenciais inválidas");
                }
            } else {
                labelLoginMsg.setText("Preencha os campos");
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}