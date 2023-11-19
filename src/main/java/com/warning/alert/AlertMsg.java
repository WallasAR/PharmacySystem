package com.warning.alert;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

public class AlertMsg {
    static ButtonType btnConfirm = new ButtonType("Confirmar");
    static ButtonType btnCancel = new ButtonType("Cancelar");
    static boolean answer;

    public static boolean msgConfirmLogout(String msg){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alerta");
        alert.setHeaderText("Confirmar saída");
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
}
