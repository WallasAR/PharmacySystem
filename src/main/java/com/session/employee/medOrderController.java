package com.session.employee;

import com.db.service.EncomendaService;
import com.example.guitest.Main;
import com.table.view.EncomendasTable;
import com.warning.alert.AlertMsg;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class medOrderController implements Initializable {
    private final EncomendaService encomendaService = new EncomendaService();

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
    @FXML
    private TextField tfUpdateStatus;
    @FXML
    private TextField tfIdStatus;

    public void tableOrder()throws SQLException{
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
        encomendaService.updateStatus(idsituação, situação);
        tableOrder();
    }
}