package com.example.guitest;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MedController {
    //@FXML
    //private Button btMinimize;

    @FXML
    protected void MainAction(MouseEvent e){ Main.changedScene("main");}
    @FXML
    protected void HomeAction(MouseEvent e) {
        Main.changedScene("home");
    }
    @FXML
    protected void FuncAction(MouseEvent e) {
        Main.changedScene("func");
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
