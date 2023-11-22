package com.session.employee;

import com.db.bank.Banco;
import com.example.guitest.Main;
import com.table.view.ClienteTable;
import com.warning.alert.AlertMsg;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

public class employeeClientController implements Initializable {

    // Ações para troca de cena
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
    protected void SaleAction(MouseEvent e) {
        Main.changedScene("sale");
    }
    @FXML
    protected void ClientAction(MouseEvent e) {
        Main.changedScene("ClientAdmFunc");
    }

    // Predefinição da instância banco
    Banco banco = new Banco();

    // GUI IDs
    @FXML
    private TableView tbCliente;
    @FXML
    private TableColumn clIdcli;
    @FXML
    private TableColumn clNomecli;
    @FXML
    private TableColumn clSobrenomeli;
    @FXML
    private TableColumn clUsuario;
    @FXML
    private TableColumn clFoneCli;
    @FXML
    private TextField tfNome;
    @FXML
    private TextField tfSobrenome;
    @FXML
    private TextField tfUser;
    @FXML
    private TextField tfFone;
    @FXML
    private TextField tfId;
    @FXML
    private TextField tfSearch;

    // Show The Client Table
    public void tabelacliente()throws SQLException {
        List<ClienteTable> clientes = new ArrayList<>();

        String consultaSQLcliente = "SELECT * FROM cliente";
        Statement statement = connection.createStatement();
        ResultSet resultado = statement.executeQuery(consultaSQLcliente);

        while (resultado.next()) {
            int valorDaColuna1 = resultado.getInt("id");
            String valorDaColuna2 = resultado.getString("nome");
            String valorDaColuna3 = resultado.getString("sobrenome");
            String valorDaColina4 = resultado.getString("usuario");
            String valorDaColuna5 = resultado.getString("telefone");

            ClienteTable cliente = new ClienteTable(valorDaColuna1, valorDaColuna2, valorDaColuna3, valorDaColina4, valorDaColuna5);
            clientes.add(cliente);
        }
        ObservableList<ClienteTable> datacli = FXCollections.observableList(clientes);

        clIdcli.setCellValueFactory(new PropertyValueFactory<>("idcli"));
        clNomecli.setCellValueFactory(new PropertyValueFactory<>("nomecli"));
        clSobrenomeli.setCellValueFactory(new PropertyValueFactory<>("sobrenome"));
        clUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        clFoneCli.setCellValueFactory(new PropertyValueFactory<>("fone"));

        FilteredList<ClienteTable> filteredLis = new FilteredList<>(datacli, b -> true);

        tfSearch.textProperty().addListener((observable, oldValue, newValue) ->{
            filteredLis.setPredicate(clienteTable -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null){
                    return true;
                }

                String searchKeyowrds = newValue.toLowerCase();

                if (clienteTable.getNomecli().toLowerCase().indexOf(searchKeyowrds) > -1) {
                    return true;
                } else if(clienteTable.getSobrenome().toLowerCase().indexOf(searchKeyowrds) > -1){
                    return true;
                } else if(clienteTable.getUsuario().toLowerCase().indexOf(searchKeyowrds) > -1){
                    return true;
                } else if(clienteTable.getFone().toLowerCase().indexOf(searchKeyowrds) > -1){
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<ClienteTable> sortedList = new SortedList<>(filteredLis);

        sortedList.comparatorProperty().bind(tbCliente.comparatorProperty());

        tbCliente.setItems(sortedList);
    }

    //Other Actions


    @FXML
    public void getItemsAction(MouseEvent event) {
        int index;
        index = tbCliente.getSelectionModel().getSelectedIndex();

        if (index <= -1){
            return;
        }

        // fill the TextFields
        tfId.setText(String.valueOf(clIdcli.getCellData(index)));
        tfNome.setText((String) clNomecli.getCellData(index));
        tfSobrenome.setText((String) clSobrenomeli.getCellData(index));
        tfUser.setText((String) clUsuario.getCellData(index));
        tfFone.setText((String) clFoneCli.getCellData(index));
    }

    public void clearTextFields(){
        tfId.setText("");tfNome.setText(""); tfSobrenome.setText(""); tfUser.setText(""); tfFone.setText("");
    }

    //Action Buttons
    public void AddClientInfoAction(javafx.event.ActionEvent event) throws SQLException{
        if (tfNome.getText().isEmpty() && tfSobrenome.getText().isEmpty() && tfUser.getText().isEmpty() && tfFone.getText().isEmpty()){
            AlertMsg alertMsg = new AlertMsg();
            alertMsg.msgInformation("Ops! Algo nos campos não parece certo","Certifique-se de preencher todos.");
        } else {
            banco.inserircliente(tfNome.getText(), tfSobrenome.getText(), tfUser.getText(), tfFone.getText());
            clearTextFields();
            tabelacliente();
        }
    }
    public void AlterClientInfoAction(javafx.event.ActionEvent event) throws SQLException {
        if (tfNome.getText().isEmpty() && tfSobrenome.getText().isEmpty() && tfUser.getText().isEmpty() && tfFone.getText().isEmpty()){
            AlertMsg alertMsg = new AlertMsg();
            alertMsg.msgInformation("Ops! Algo nos campos não parece certo","Certifique-se de preencher todos.");
        } else {
            banco.updateClient(tfNome.getText(), tfSobrenome.getText(), tfUser.getText(), tfFone.getText(), tfId.getText());
            clearTextFields();
            tabelacliente();
        }
    }
    public void RemoveClientInfoAction(javafx.event.ActionEvent event) throws SQLException{
        if (tfNome.getText().isEmpty() && tfSobrenome.getText().isEmpty() && tfUser.getText().isEmpty() && tfFone.getText().isEmpty()){
            AlertMsg alertMsg = new AlertMsg();
            alertMsg.msgInformation("Ops! Algo nos campos não parece certo","Certifique-se de preencher todos.");

        } else if (AlertMsg.msgConfirm("Confirmação de exclusão", "Deseja remover o cliente " + tfNome.getText() + " do sistema?")){
            banco.deletarcliente(tfId.getText());
            clearTextFields();
            tabelacliente();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // modifica o campo ID somente para leitura
        tfId.setDisable(true);

        // Mostra a tabela de clientes assim que a pagina é aberta
        try {
            tabelacliente();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

