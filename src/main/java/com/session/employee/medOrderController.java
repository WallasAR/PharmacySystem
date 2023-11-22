package com.session.employee;

import com.example.guitest.Main;
import com.table.view.EncomendasTable;
import com.warning.alert.AlertMsg;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import static com.db.bank.Banco.connection;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class medOrderController implements Initializable {

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
    protected void ClientAction(MouseEvent e) {
        Main.changedScene("ClientAdmFunc");
    }


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
    @FXML
    private TextField tfUpdateStatus;
    @FXML
    private TextField tfIdStatus;

    public void tableOrder()throws SQLException{
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

        FilteredList<EncomendasTable> filteredLis = new FilteredList<>(dateOrder, b -> true);

        tfSearch.textProperty().addListener((observable, oldValue, newValue) ->{
            filteredLis.setPredicate(encomendasTable -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null){
                    return true;
                }

                String searchKeyowrds = newValue.toLowerCase();

                if (encomendasTable.getUser().toLowerCase().indexOf(searchKeyowrds) > -1) {
                    return true;
                } else if(encomendasTable.getMedicine().toLowerCase().indexOf(searchKeyowrds) > -1){
                    return true;
                } else if(encomendasTable.getDate().toLowerCase().indexOf(searchKeyowrds) > -1){
                    return true;
                } else if(encomendasTable.getPhone().toLowerCase().indexOf(searchKeyowrds) > -1){
                    return true;
                } else if(encomendasTable.getStatus().toLowerCase().indexOf(searchKeyowrds) > -1){
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<EncomendasTable> sortedList = new SortedList<>(filteredLis);

        sortedList.comparatorProperty().bind(tvOrder.comparatorProperty());

        tvOrder.setItems(sortedList);
    }

    public void updateTableAction(javafx.event.ActionEvent event) throws SQLException{
        tableOrder();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            tableOrder();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void AlterarSituaçãoPedido(javafx.event.ActionEvent event)throws SQLException {
        int idsituação = Integer.parseInt(tfIdStatus.getText());
        String situação = tfUpdateStatus.getText();
        String uptadasituação = "UPDATE encomendas SET status = ? WHERE id = ?";
        PreparedStatement updatesitu = connection.prepareStatement(uptadasituação);
        updatesitu.setString(1, situação);
        updatesitu.setInt(2, idsituação);
        updatesitu.executeUpdate();
        tableOrder();
    }
}