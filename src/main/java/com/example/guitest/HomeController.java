package com.example.guitest;

import com.db.bank.Banco;
import com.db.service.EncomendaService;
import com.table.view.EncomendasTable;
import com.warning.alert.AlertMsg;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    private final EncomendaService encomendaService = new EncomendaService();

    @FXML
    protected void MainAction(MouseEvent e) {
    if (AlertMsg.msgConfirm("Confimar Logout", "Deseja sair para a página de login?")) {
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
    protected void RecordAction(MouseEvent e) {
        Main.changedScene("record");
    }

    @FXML
    private Label labelFuncCount;
    @FXML
    private Label labelMedCount;
    @FXML
    private Label labelClientCount;

    @FXML
    private TableColumn<EncomendasTable, String> tcDateOrder;

    @FXML
    private TableColumn<EncomendasTable, Integer> tcIdOrder;

    @FXML
    private TableColumn<EncomendasTable, String> tcMedOrder;

    @FXML
    private TableColumn<EncomendasTable, String> tcPhoneOrder;

    @FXML
    private TableColumn<EncomendasTable, Float> tcPriceOrder;

    @FXML
    private TableColumn<EncomendasTable, Integer> tcQuantOrder;

    @FXML
    private TableColumn<EncomendasTable, String> tcStatusOrder;

    @FXML
    private TableColumn<EncomendasTable, String> tcUserOrder;

    @FXML
    private TableView<EncomendasTable> tvOrder;

    @FXML
    private TextField tfSearch;

    public void tableOrder()throws SQLException {
        var order = encomendaService.listAll();
        ObservableList<EncomendasTable> dateOrder = FXCollections.observableList(order);

        tcIdOrder.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcUserOrder.setCellValueFactory(new PropertyValueFactory<>("user"));
        tcMedOrder.setCellValueFactory(new PropertyValueFactory<>("medicine"));
        tcQuantOrder.setCellValueFactory(new PropertyValueFactory<>("amount"));
        tcPriceOrder.setCellValueFactory(new PropertyValueFactory<>("price"));
        tcDateOrder.setCellValueFactory(new PropertyValueFactory<>("date"));
        tcPhoneOrder.setCellValueFactory(new PropertyValueFactory<>("phone"));
        tcStatusOrder.setCellValueFactory(new PropertyValueFactory<>("status"));

        tvOrder.setItems(dateOrder);
    }
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

        try {
            tableOrder();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}