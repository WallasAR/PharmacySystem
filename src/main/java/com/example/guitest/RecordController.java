package com.example.guitest;

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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.db.bank.Banco.connection;

public class RecordController implements Initializable {

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
    private TableColumn tcDateRecord;

    @FXML
    private TableColumn tcIdRecord;

    @FXML
    private TableColumn tcMedRecord;

    @FXML
    private TableColumn tcPriceRecord;

    @FXML
    private TableColumn tcQuantRecord;

    @FXML
    private TableColumn tcUserRecord;

    @FXML
    private TableView tvRecord;

    public void tableRecord()throws SQLException {
        List<RecordTable> order = new ArrayList<>();

        String consultaSQLregistro = "SELECT * FROM registros";
        Statement statement = connection.createStatement();
        ResultSet resultado = statement.executeQuery(consultaSQLregistro);

        while (resultado.next()) {
            int valorDaColuna1 = resultado.getInt("id");
            String valorDaColuna2 = resultado.getString("usuario");
            String valorDaColuna3 = resultado.getString("medicamento");
            int valorDaColuna4 = resultado.getInt("quantidade");
            float valorDaColuna5 = resultado.getFloat("valor");
            String valorDaColuna6 = resultado.getString("data");

            RecordTable recordTable = new RecordTable(valorDaColuna1, valorDaColuna2, valorDaColuna3, valorDaColuna4, valorDaColuna5, valorDaColuna6);
            order.add(recordTable);
        }
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
