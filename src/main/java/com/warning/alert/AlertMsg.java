package com.warning.alert;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

public class AlertMsg {
    static ButtonType btnConfirm = new ButtonType("Confirmar");
    static ButtonType btnCancel = new ButtonType("Cancelar");
    static boolean answer;

    public static boolean msgConfirm(String headermsg, String msg){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alerta");
        alert.setHeaderText(headermsg);
        alert.setContentText(msg);
        alert.getButtonTypes().setAll(btnConfirm, btnCancel);
        alert.showAndWait().ifPresent(b -> {
            if (b == btnConfirm){
                answer = true;
            } else {
                answer = false;
            }
        });
        return answer;
    }

    public void msgInformation(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Aviso");
        alert.setHeaderText("Ops! Algo nos campos não parece certo");
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
