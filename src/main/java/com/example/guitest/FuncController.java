package com.example.guitest;

import com.warning.alert.AlertMsg;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class FuncController {
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
    protected void MedAction(MouseEvent e) {
        Main.changedScene("med");
    }
    @FXML
    protected void ClientAction(MouseEvent e) {
        Main.changedScene("client");
    }

    /*public void MinimizeClicked(MouseEvent event){
        Stage stage = (Stage) btMinimize.getScene().getWindow(); // Atribui a variavel a capacidade de minimar a tela
        stage.setIconified(true);
    }*/
}
