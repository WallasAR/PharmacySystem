package com.example.guitest;

import com.warning.alert.AlertMsg;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;


public class ClientController {
    //@FXML
    //private Button btMinimize;

    @FXML
    protected void MainAction(MouseEvent e) {
        if (AlertMsg.msgConfirmLogout("Deseja sair para a página de login?")) {
            Main.changedScene("main");
        }
    }
    @FXML
    protected void HomeAction(MouseEvent e) {
        Main.changedScene("home");
    }
    @FXML
    protected void FuncAction(MouseEvent e) {
        Main.changedScene("func");
    }
    @FXML
    protected void MedAction(MouseEvent e) {
        Main.changedScene("med");
    }


    /*public void MinimizeClicked(MouseEvent event){
        Stage stage = (Stage) btMinimize.getScene().getWindow(); // Atribui a variavel a capacidade de minimar a tela
        stage.setIconified(true);
    }*/
}
