package com.session.employee;

import com.db.bank.DatabaseConnection;
import com.db.service.PurchaseService;
import com.example.guitest.Main;
import com.table.view.CarrinhoTable;
import com.table.view.MedicamentoTable;
import com.warning.alert.AlertMsg;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Date;
import java.text.SimpleDateFormat;

public class PurchaseController implements Initializable {
    private final PurchaseService purchaseService = new PurchaseService();


    @FXML
    private TableView<CarrinhoTable> tvCarrinho;
    @FXML
    private TableView<MedicamentoTable> tvCompra;
    @FXML
    private TableColumn<MedicamentoTable, Integer> tcIdmedi;
    @FXML
    private TableColumn<MedicamentoTable, String> tcNomemedi;
    @FXML
    private TableColumn<MedicamentoTable, Integer> tcQuantimedi;
    @FXML
    private TableColumn<MedicamentoTable, String> tcTipomedi;
    @FXML
    private TableColumn<MedicamentoTable, Float> tcPreçomedi;
    @FXML
    private TableColumn<?, ?> tcUser;
    @FXML
    private TableColumn<CarrinhoTable, String> tfUser;
    @FXML
    private TableColumn<CarrinhoTable, Integer> tfIdmedi;
    @FXML
    private TableColumn<CarrinhoTable, String> tfNomemedi;
    @FXML
    private TableColumn<CarrinhoTable, Integer> tfQuantimedi;

    @FXML
    private TableColumn<CarrinhoTable, Float> tfPreçomedi;
    @FXML
    private TextField tfSearch;
    @FXML
    private TextField tfIdCarrinho;
    @FXML
    private TextField tfNome;
    @FXML
    private TextField tfQuantidade;
    @FXML
    private TextField tfTipo;
    @FXML
    private TextField tfValor;
    @FXML
    private TextField tfId;
    @FXML
    private ComboBox<String> Box;
    @FXML
    private Label labelShowTotal;

    @FXML
    protected void MainAction(MouseEvent e) {
        if (AlertMsg.msgConfirm("Confimar Logout","Deseja sair para a página de login?")) {
            Main.changedScene("main");
        }
    }
    @FXML
    protected void MedOrderAction(MouseEvent e) {
        Main.changedScene("medOrder");
    }
    @FXML
    protected void ClientAction(MouseEvent e) {
        Main.changedScene("ClientAdmFunc");
    }

    public void tabelamedi() throws SQLException {
        List<MedicamentoTable> medicamentos = new ArrayList<>();

        String consultaSQL = "SELECT * FROM medicamentos";
        try (Connection db = DatabaseConnection.open();
             Statement statement = db.createStatement();
             ResultSet resultado = statement.executeQuery(consultaSQL)) {
            while (resultado.next()) {
                int valorDaColuna1 = resultado.getInt("id");
                String valorDaColuna2 = resultado.getString("nome");
                int valorDaColuna3 = resultado.getInt("quantidade");
                String valorDaColina4 = resultado.getString("tipo");
                Float valorDaColuna5 = resultado.getFloat("valor");

                MedicamentoTable medicamento = new MedicamentoTable(valorDaColuna1, valorDaColuna2, valorDaColuna3, valorDaColina4, valorDaColuna5);
                medicamentos.add(medicamento);
            }
        }

        ObservableList<MedicamentoTable> datamedi = FXCollections.observableList(medicamentos);

        tcIdmedi.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcNomemedi.setCellValueFactory(new PropertyValueFactory<>("nomemedi"));
        tcQuantimedi.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        tcTipomedi.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        tcPreçomedi.setCellValueFactory(new PropertyValueFactory<>("valor"));

        tvCompra.setItems(datamedi);


        FilteredList<MedicamentoTable> filteredLis = new FilteredList<>(datamedi, b -> true);

        tfSearch.textProperty().addListener((observable, oldValue, newValue) ->{
            filteredLis.setPredicate(funcionarioTable -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null){
                    return true;
                }

                String searchKeyowrds = newValue.toLowerCase();

                if (funcionarioTable.getNomemedi().toLowerCase().indexOf(searchKeyowrds) > -1) {
                    return true;
                } else if(funcionarioTable.getTipo().toLowerCase().indexOf(searchKeyowrds) > -1){
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<MedicamentoTable> sortedList = new SortedList<>(filteredLis);

        sortedList.comparatorProperty().bind(tvCompra.comparatorProperty());

        tvCompra.setItems(sortedList);
    }
    // TableView Medicamentos GetItems
    public void getItemsActionCompra(MouseEvent event)throws SQLException {
        int index;
        index = tvCompra.getSelectionModel().getSelectedIndex();

        if (index <= -1){
            return;
        }
        String consultaSQLcliente = "SELECT * FROM cliente";
        Box.getItems().clear();
        try (Connection db = DatabaseConnection.open();
             Statement statement = db.createStatement();
             ResultSet resultado = statement.executeQuery(consultaSQLcliente)) {
            while (resultado.next()) {
                String valorDaColuna4 = resultado.getString("usuario");

                List<String> usercliente= new ArrayList<>();
                usercliente.add(valorDaColuna4);
                ObservableList<String> Cliente = FXCollections.observableList(usercliente);
                Box.getItems().addAll(Cliente);
            }
        }

        // fill the TextFields
        tfId.setText(String.valueOf(tcIdmedi.getCellData(index)));
        tfNome.setText((String) tcNomemedi.getCellData(index));
        tfTipo.setText((String) tcTipomedi.getCellData(index));
        Float preco = tcPreçomedi.getCellData(index);
        tfValor.setText(preco == null ? "" : String.valueOf(preco));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tfId.setDisable(true);
        tfNome.setDisable(true);
        tfTipo.setDisable(true);
        tfValor.setDisable(true);

        try {
            tabelamedi();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void ViewBox()throws SQLException{
        String consultaSQLcliente = "SELECT usuario FROM cliente";
        Box.getItems().clear();
        try (Connection db = DatabaseConnection.open();
             Statement statement = db.createStatement();
             ResultSet resultado = statement.executeQuery(consultaSQLcliente)) {
            while (resultado.next()) {
                String valorDaColuna4 = resultado.getString("usuario");

                List<String> usercliente= new ArrayList<>();
                usercliente.add(valorDaColuna4);
                ObservableList<String> Cliente = FXCollections.observableList(usercliente);
                Box.getItems().addAll(Cliente);
            }
        }
    }
    public void colocarRegistro(javafx.event.ActionEvent event) throws SQLException {
        String user = selectedUser();
        if (user == null) {
            return;
        }
        String nome = tfNome.getText();
        int quantidade = Integer.parseInt(tfQuantidade.getText());
        float valor = Float.parseFloat(tfValor.getText());
        if (quantidade <= 0) {
            return;
        }
        PurchaseService.MedicationAvailability availability = purchaseService.getMedicationAvailability(user, nome);
        if (availability == null) {
            return;
        }

        if (availability.stockQuantity() == 0) {
            if (AlertMsg.msgConfirm("A quantidade do medicamento selecionado é: " + availability.stockQuantity(), "Faça um agendamento.")) {
                float total = valor * quantidade;
                String date = new SimpleDateFormat("dd/MM/yyyy | HH:mm:ss").format(new Date());
                purchaseService.createOrder(user, nome, quantidade, total, date, availability.clientPhone(), "Pedido Efetuado");
            }
            return;
        }

        if (quantidade > availability.stockQuantity()) {
            return;
        }

        boolean requirePrescription = availability.medicationType().equalsIgnoreCase("TarjaPreta")
                || availability.medicationType().equalsIgnoreCase("TarjaVermelha");

        if (requirePrescription) {
            String title = availability.medicationType().equalsIgnoreCase("TarjaPreta")
                    ? "Alerta de tarja preta! O medicamento " + availability.medicationName() + " necessita de receita"
                    : "Alerta de tarja vermelha! O medicamento " + availability.medicationName() + " necessita de receita dupla";
            if (!AlertMsg.msgConfirm(title, "Solicite-a ao cliente.")) {
                return;
            }
        }

        float total = valor * quantidade;
        purchaseService.addToCartAndDecreaseStock(user, nome, quantidade, total);
        tabelamedi();
        Carrinho();
    }
    public void Carrinho() throws SQLException {
        int index = tvCarrinho.getSelectionModel().getSelectedIndex();

        if (index <= -1) {
            String user = selectedUser();
            if (user == null) {
                return;
            }
            List<CarrinhoTable> carrinho = purchaseService.listCartByUser(user);
            ObservableList<CarrinhoTable> data = FXCollections.observableList(carrinho);

            tfIdmedi.setCellValueFactory(new PropertyValueFactory<>("id"));
            tfUser.setCellValueFactory(new PropertyValueFactory<>("usuario"));
            tfNomemedi.setCellValueFactory(new PropertyValueFactory<>("nomeMed"));
            tfQuantimedi.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
            tfPreçomedi.setCellValueFactory(new PropertyValueFactory<>("valor"));

            tvCarrinho.setItems(data);

            float total = purchaseService.sumCartTotal(user);
            labelShowTotal.setText(String.format("%.2f", total));
        }
    }
    public void ConfirmarCompra(javafx.event.ActionEvent event)throws SQLException{
        String user = selectedUser();
        if (user == null) {
            return;
        }
        String nome = tfNome.getText();
        int quantidade = Integer.parseInt(tfQuantidade.getText());
        float valor = Float.parseFloat(tfValor.getText());
        String date = new SimpleDateFormat("dd/MM/yyyy | HH:mm:ss").format(new Date());
        float total = valor * quantidade;
        purchaseService.confirmPurchase(user, nome, quantidade, total, date);

        AlertMsg info = new AlertMsg();
        info.msgInformation("Confirmação de venda", "Venda realizada com sucesso!");
        Carrinho();
    }
    public void RemoverDoCarrinho(javafx.event.ActionEvent event)throws SQLException{
        int iduser = Integer.parseInt(tfIdCarrinho.getText());
        if (AlertMsg.msgConfirm("Remoção de Pedido", "Tem certeza?")) {
            purchaseService.removeCartItemAndRestoreStock(iduser);
            tabelamedi();
            Carrinho();
        }
    }
    public void AtualizarCarrinho(javafx.event.ActionEvent event)throws SQLException {
        Carrinho();
    }

    public void UpdateTableMed(javafx.event.ActionEvent event) throws SQLException{
        tabelamedi();
    }

    private String selectedUser() {
        Object selected = Box.getSelectionModel().getSelectedItem();
        return selected == null ? null : selected.toString();
    }
}
