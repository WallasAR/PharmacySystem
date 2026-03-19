package com.example.guitest;

import com.db.service.RegistroService;
import com.table.view.RecordTable;
import com.warning.alert.AlertMsg;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RecordController implements Initializable {
    private final RegistroService registroService = new RegistroService();

    @FXML
    protected void MainAction(MouseEvent e) {
        if (AlertMsg.msgConfirm("Confimar Logout","Deseja sair para a página de login?")) {
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
    @FXML
    protected void ClientAction(MouseEvent e) {
        Main.changedScene("client");
    }

    @FXML
    private TableColumn<RecordTable, String> tcDateRecord;

    @FXML
    private TableColumn<RecordTable, Integer> tcIdRecord;

    @FXML
    private TableColumn<RecordTable, String> tcMedRecord;

    @FXML
    private TableColumn<RecordTable, Float> tcPriceRecord;

    @FXML
    private TableColumn<RecordTable, Integer> tcQuantRecord;

    @FXML
    private TableColumn<RecordTable, String> tcUserRecord;

    @FXML
    private TableView<RecordTable> tvRecord;

    public void tableRecord()throws SQLException {
        var order = registroService.listAll();
        ObservableList<RecordTable> dateRecord = FXCollections.observableList(order);

        tcIdRecord.setCellValueFactory(new PropertyValueFactory<>("idR"));
        tcUserRecord.setCellValueFactory(new PropertyValueFactory<>("userR"));
        tcMedRecord.setCellValueFactory(new PropertyValueFactory<>("medicineR"));
        tcQuantRecord.setCellValueFactory(new PropertyValueFactory<>("amountR"));
        tcPriceRecord.setCellValueFactory(new PropertyValueFactory<>("priceR"));
        tcDateRecord.setCellValueFactory(new PropertyValueFactory<>("dateR"));

        tvRecord.setItems(dateRecord);
    }

    public void updateTable(javafx.event.ActionEvent event) throws SQLException {
        tableRecord();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            tableRecord();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
