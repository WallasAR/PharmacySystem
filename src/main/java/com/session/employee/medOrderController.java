package com.session.employee;

import com.example.guitest.Main;
import com.warning.alert.AlertMsg;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import com.table.view.MedicamentoTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import static com.db.bank.Banco.connection;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class medOrderController  {

    @FXML
    protected void MainAction(MouseEvent e) {
        if (AlertMsg.msgConfirm("Confimar Logout","Deseja sair para a página de login?")) {
            Main.changedScene("main");
        }
    }
    @FXML
    protected void SaleAction(MouseEvent e) {
        Main.changedScene("sale");
    }
    @FXML
    protected void MedOrderAction(MouseEvent e) {
        Main.changedScene("medOrder");
    }
}
