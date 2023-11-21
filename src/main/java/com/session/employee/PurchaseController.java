package com.session.employee;

import com.db.bank.Banco;
import com.example.guitest.LoginController;
import com.example.guitest.Main;
import com.table.view.CarrinhoTable;
import com.table.view.ClienteTable;
import com.table.view.FuncionarioTable;
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Date;
import java.text.SimpleDateFormat;

import static com.db.bank.Banco.connection;

public class PurchaseController implements Initializable {


    @FXML
    private TableView tvCarrinho;
    @FXML
    private TableView tvCompra;
    @FXML
    private TableColumn tcIdmedi;
    @FXML
    private TableColumn tcNomemedi;
    @FXML
    private TableColumn tcQuantimedi;
    @FXML
    private TableColumn tcTipomedi;
    @FXML
    private TableColumn tcPreçomedi;
    @FXML
    private TableColumn tcUser;
    @FXML
    private TableColumn tfUser;
    @FXML
    private TableColumn tfIdmedi;
    @FXML
    private TableColumn tfNomemedi;
    @FXML
    private TableColumn tfQuantimedi;

    @FXML
    private TableColumn tfPreçomedi;
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
    private ComboBox Box;
    @FXML
    private Label labelShowTotal;

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

    public void tabelamedi() throws SQLException {
        List<MedicamentoTable> medicamentos = new ArrayList<>();

        String consultaSQL = "SELECT * FROM medicamentos";
        Statement statement = connection.createStatement();
        ResultSet resultado = statement.executeQuery(consultaSQL);

        while (resultado.next()) {
            int valorDaColuna1 = resultado.getInt("id");
            String valorDaColuna2 = resultado.getString("nome");
            int valorDaColuna3 = resultado.getInt("quantidade");
            String valorDaColina4 = resultado.getString("tipo");
            Float valorDaColuna5 = resultado.getFloat("valor");

            MedicamentoTable medicamento = new MedicamentoTable(valorDaColuna1, valorDaColuna2, valorDaColuna3, valorDaColina4, valorDaColuna5);
            medicamentos.add(medicamento);
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
        List<ClienteTable> clientes = new ArrayList<>();

        String consultaSQLcliente = "SELECT * FROM cliente";
        Statement statement = connection.createStatement();
        ResultSet resultado = statement.executeQuery(consultaSQLcliente);

        while (resultado.next()) {
            int valorDaColuna1 = resultado.getInt("id");
            String valorDaColuna2 = resultado.getString("nome");
            String valorDaColuna3 = resultado.getString("sobrenome");
            String valorDaColuna4 = resultado.getString("usuario");
            String valorDaColuna5 = resultado.getString("telefone");

            ClienteTable cliente = new ClienteTable(valorDaColuna1, valorDaColuna2, valorDaColuna3, valorDaColuna4, valorDaColuna5);
            clientes.add(cliente);

            List<String> usercliente= new ArrayList<>();
            usercliente.add(valorDaColuna4);
            ObservableList<String> Cliente = FXCollections.observableList(usercliente);
            Box.getItems().addAll(Cliente);
        }

        // fill the TextFields
        tfId.setText(String.valueOf(tcIdmedi.getCellData(index)));
        tfNome.setText((String) tcNomemedi.getCellData(index));
        tfTipo.setText((String) tcTipomedi.getCellData(index));
        tfValor.setText(String.valueOf((float) tcPreçomedi.getCellData(index)));
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
        Statement statement = connection.createStatement();
        ResultSet resultado = statement.executeQuery(consultaSQLcliente);

        while (resultado.next()) {
            String valorDaColuna4 = resultado.getString("usuario");

            List<String> usercliente= new ArrayList<>();
            usercliente.add(valorDaColuna4);
            ObservableList<String> Cliente = FXCollections.observableList(usercliente);
            Box.getItems().addAll(Cliente);
        }
    }
    public void colocarRegistro(javafx.event.ActionEvent event) throws SQLException {
        Banco banco = new Banco();
        String user = Box.getSelectionModel().getSelectedItem().toString();
        String nome = tfNome.getText();
        int quantidade = Integer.parseInt(tfQuantidade.getText());
        float valor = Float.parseFloat(tfValor.getText());
        String tipo = tfTipo.getText();


        String consultatipo = "SELECT nome, quantidade, tipo FROM medicamentos WHERE nome = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(consultatipo);
        preparedStatement.setString(1, nome);
        ResultSet resultado = preparedStatement.executeQuery();
        String nomemedicamento = null;
        while(resultado.next()) {
            String tipomedi = resultado.getString("tipo");
            nomemedicamento = resultado.getString("nome");
            int quantidadebanco = resultado.getInt("quantidade");
            if (tipomedi.equalsIgnoreCase("TarjaPreta")) {
                AlertMsg alert = new AlertMsg();
                if (alert.msgConfirm("Alerta de tarja preta! O medicamento " + nomemedicamento + " necessita de receita", "Solicite-a ao cliente.")) {
                    float novoValor = valor * quantidade;

                    // Date system to table registro
                    int novaquanti = quantidadebanco - quantidade;
                    banco.inserircarrinho(user, nome, quantidade, novoValor);
                    String updateselect1 = "UPDATE medicamentos SET quantidade = ? WHERE nome = ?";
                    try (PreparedStatement preparedStatementselect = connection.prepareStatement(updateselect1)) {
                        preparedStatementselect.setInt(1, novaquanti);
                        preparedStatementselect.setString(2, nomemedicamento);

                        preparedStatementselect.executeUpdate();
                        tabelamedi();
                        Carrinho();
                    }
                    break;
                } else {
                }
            } else if (tipomedi.equalsIgnoreCase("TarjaVermelha")) {
                AlertMsg alert = new AlertMsg();
                if (alert.msgConfirm("Alerta de tarja vermelha! O medicamento " + nomemedicamento + " necessita de receita dupla", "Solicite-a ao cliente.")) {
                    float novoValor = valor * quantidade;

                    // Date system to table registro
                    int novaquanti = quantidadebanco - quantidade;
                    banco.inserircarrinho(user, nome, quantidade, novoValor);
                    String updateselect1 = "UPDATE medicamentos SET quantidade = ? WHERE nome = ?";
                    try (PreparedStatement preparedStatementselect = connection.prepareStatement(updateselect1)) {
                        preparedStatementselect.setInt(1, novaquanti);
                        preparedStatementselect.setString(2, nomemedicamento);

                        preparedStatementselect.executeUpdate();
                        tabelamedi();
                        Carrinho();
                    }
                    break;
                } else {
                }
            } else if (tipo.equalsIgnoreCase("SemTarja")) {
                float novoValor = valor * quantidade;
                // Date system to table registro
                int novaquanti = quantidadebanco - quantidade;
                banco.inserircarrinho(user, nome, quantidade, novoValor);
                String updateselect1 = "UPDATE medicamentos SET quantidade = ? WHERE nome = ?";
                try (PreparedStatement preparedStatementselect = connection.prepareStatement(updateselect1)) {
                    preparedStatementselect.setInt(1, novaquanti);
                    preparedStatementselect.setString(2, nomemedicamento);

                    preparedStatementselect.executeUpdate();
                    tabelamedi();
                    Carrinho();
                }
                break;
            }
        }
    }
    public void Carrinho() throws SQLException {
        int index = tvCarrinho.getSelectionModel().getSelectedIndex();

        if (index <= -1) {
            List<CarrinhoTable> carrinho = new ArrayList<>();
            String user = Box.getSelectionModel().getSelectedItem().toString();
            String consultaSQLcliente = "SELECT * FROM carrinho WHERE usuario = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(consultaSQLcliente)) {
                preparedStatement.setString(1, user);

                try (ResultSet resultado = preparedStatement.executeQuery()) {
                    while (resultado.next()) {
                        int valorDaColuna1 = resultado.getInt("id");
                        String valorDaColuna2 = resultado.getString("usuario");
                        String valorDaColuna3 = resultado.getString("medicamento");
                        int valorDaColuna4 = resultado.getInt("quantidade");
                        float valorDaColuna5 = resultado.getFloat("valor");

                        CarrinhoTable Carrinhocliente = new CarrinhoTable(valorDaColuna1, valorDaColuna2, valorDaColuna3, valorDaColuna4, valorDaColuna5);
                        carrinho.add(Carrinhocliente);
                    }
                }
            }

            ObservableList<CarrinhoTable> datamedi = FXCollections.observableList(carrinho);

            tfIdmedi.setCellValueFactory(new PropertyValueFactory<>("id"));
            tfUser.setCellValueFactory(new PropertyValueFactory<>("usuario"));
            tfNomemedi.setCellValueFactory(new PropertyValueFactory<>("nomeMed"));
            tfQuantimedi.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
            tfPreçomedi.setCellValueFactory(new PropertyValueFactory<>("valor"));

            tvCarrinho.setItems(datamedi);

            try {
                String consultaSQLCliente = "SELECT SUM(valor) AS soma_total FROM carrinho WHERE usuario = ?";

                try (PreparedStatement statement = connection.prepareStatement(consultaSQLCliente)) {
                    statement.setString(1, user);
                    ResultSet consultaSoma = statement.executeQuery();

                    if (consultaSoma.next()) {
                        float soma = consultaSoma.getFloat("soma_total");
                        labelShowTotal.setText(String.valueOf(soma));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                }
        }
    }
    public void ConfirmarCompra(javafx.event.ActionEvent event)throws SQLException{
        Banco banco = new Banco();
        String user = Box.getSelectionModel().getSelectedItem().toString();
        String nome = tfNome.getText();
        int quantidade = Integer.parseInt(tfQuantidade.getText());
        float valor = Float.parseFloat(tfValor.getText());
        String tipo = tfTipo.getText();
        Date dataHoraAtual = new Date();
        String date = new SimpleDateFormat("dd/MM/yyyy | HH:mm:ss").format(dataHoraAtual);

        String consultatipo = "SELECT nome, quantidade, tipo FROM medicamentos WHERE tipo = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(consultatipo);
        preparedStatement.setString(1, tipo);
        ResultSet resultado = preparedStatement.executeQuery();
        while(resultado.next()) {
            String tipomedi = resultado.getString("tipo");
            String nomemedicamento = resultado.getString("nome");
            int quantidadebanco = resultado.getInt("quantidade");

            float novaquanti = quantidadebanco - quantidade;
            banco.inseriregistro(user, nome, quantidade, novaquanti, date);
            String deletecli = ("DELETE FROM carrinho WHERE usuario = ?");
            preparedStatement = connection.prepareStatement(deletecli);
            preparedStatement.setString(1, user);
            preparedStatement.executeUpdate();
            Carrinho();
            break;
        }
    }
    public void RemoverDoCarrinho(javafx.event.ActionEvent event)throws SQLException{

        int iduser = Integer.parseInt(tfIdCarrinho.getText());
        String user = Box.getSelectionModel().getSelectedItem().toString();
        String deletecli = ("DELETE FROM carrinho WHERE id = ? AND usuario = ?");
        PreparedStatement preparedStatement = connection.prepareStatement(deletecli);
        preparedStatement.setInt(1, iduser);
        preparedStatement.setString(2, user);
        preparedStatement.executeUpdate();
        Carrinho();
    }
    public void AtualizarCarrinho(javafx.event.ActionEvent event)throws SQLException {
        int index = tvCarrinho.getSelectionModel().getSelectedIndex();

        if (index <= -1) {
            List<CarrinhoTable> carrinho = new ArrayList<>();
            String user = Box.getSelectionModel().getSelectedItem().toString();
            String consultaSQLcliente = "SELECT * FROM carrinho WHERE usuario = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(consultaSQLcliente)) {
                preparedStatement.setString(1, user);

                try (ResultSet resultado = preparedStatement.executeQuery()) {
                    while (resultado.next()) {
                        int valorDaColuna1 = resultado.getInt("id");
                        String valorDaColuna2 = resultado.getString("usuario");
                        String valorDaColuna3 = resultado.getString("medicamento");
                        int valorDaColuna4 = resultado.getInt("quantidade");
                        float valorDaColuna5 = resultado.getFloat("valor");

                        CarrinhoTable Carrinhocliente = new CarrinhoTable(valorDaColuna1, valorDaColuna2, valorDaColuna3, valorDaColuna4, valorDaColuna5);
                        carrinho.add(Carrinhocliente);
                    }
                }
            }

            ObservableList<CarrinhoTable> datamedi = FXCollections.observableList(carrinho);

            tfIdmedi.setCellValueFactory(new PropertyValueFactory<>("id"));
            tfUser.setCellValueFactory(new PropertyValueFactory<>("usuario"));
            tfNomemedi.setCellValueFactory(new PropertyValueFactory<>("nomeMed"));
            tfQuantimedi.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
            tfPreçomedi.setCellValueFactory(new PropertyValueFactory<>("valor"));

            tvCarrinho.setItems(datamedi);

            try {
                String consultaSQLCliente = "SELECT SUM(valor) AS soma_total FROM carrinho WHERE usuario = ?";

                try (PreparedStatement statement = connection.prepareStatement(consultaSQLCliente)) {
                    statement.setString(1, user);
                    ResultSet consultaSoma = statement.executeQuery();

                    if (consultaSoma.next()) {
                        float soma = consultaSoma.getFloat("soma_total");
                        labelShowTotal.setText(String.valueOf(soma));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
