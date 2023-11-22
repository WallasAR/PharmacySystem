package com.example.guitest;

import com.db.bank.Banco;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.db.bank.Banco.connection;

public class HomeController implements Initializable {

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
    private TableColumn tcDateOrder;

    @FXML
    private TableColumn tcIdOrder;

    @FXML
    private TableColumn tcMedOrder;

    @FXML
    private TableColumn tcPhoneOrder;

    @FXML
    private TableColumn tcPriceOrder;

    @FXML
    private TableColumn tcQuantOrder;

    @FXML
    private TableColumn tcStatusOrder;

    @FXML
    private TableColumn tcUserOrder;

    @FXML
    private TableView tvOrder;

    @FXML
    private TextField tfSearch;

    public void tableOrder()throws SQLException {
        List<EncomendasTable> order = new ArrayList<>();

        String consultaSQLcliente = "SELECT * FROM encomendas";
        Statement statement = connection.createStatement();
        ResultSet resultado = statement.executeQuery(consultaSQLcliente);

        while (resultado.next()) {
            int valorDaColuna1 = resultado.getInt("id");
            String valorDaColuna2 = resultado.getString("usuario");
            String valorDaColuna3 = resultado.getString("medicamento");
            int valorDaColuna4 = resultado.getInt("quantidade");
            float valorDaColuna5 = resultado.getFloat("valor");
            String valorDaColuna6 = resultado.getString("data");
            String valorDaColuna7 = resultado.getString("telefone");
            String valorDaColuna8 = resultado.getString("status");

            EncomendasTable orderTable = new EncomendasTable(valorDaColuna1, valorDaColuna2, valorDaColuna3, valorDaColuna4, valorDaColuna5, valorDaColuna6, valorDaColuna7, valorDaColuna8);
            order.add(orderTable);
        }
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