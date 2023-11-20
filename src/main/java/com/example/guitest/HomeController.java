package com.example.guitest;

import com.db.bank.Banco;
import com.example.guitest.Main;
import com.warning.alert.AlertMsg;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    protected void MainAction(MouseEvent e) {
    if (AlertMsg.msgConfirmLogout("Confimar Logout", "Deseja sair para a página de login?")) {
            Main.changedScene("main");
        }
    }
    @FXML
    protected void FuncAction(MouseEvent e) {
        Main.changedScene("func");
    }
    @FXML
    protected void MedAction(MouseEvent e) {
        Main.changedScene("med");
    }
    @FXML
    protected void ClientAction(MouseEvent e) {
        Main.changedScene("client");
    }

    @FXML
    private Label labelFuncCount;
    @FXML
    private Label labelMedCount;
    @FXML
    private Label labelClientCount;


    int resultFunc;
    int resultMed;
    int resultClient;
    public void showInf(){
        resultFunc = Banco.somarentidadesefuncionarios();
        resultMed = Banco.somarentidadesmedicamentos();
        resultClient = Banco.somarentidadeseliente();

        labelFuncCount.setText(String.valueOf(resultFunc));
        labelMedCount.setText(String.valueOf(resultMed));
        labelClientCount.setText(String.valueOf(resultClient));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showInf();
    }
}