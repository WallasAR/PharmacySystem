package com.example.guitest;

import com.db.bank.Banco;
import com.table.view.ClienteTable;
import com.table.view.FuncionarioTable;
import com.warning.alert.AlertMsg;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import static com.db.bank.Banco.connection;

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

public class FuncController implements Initializable {
    //@FXML
    //private Button btMinimize;
    Banco banco = new Banco();
    @FXML
    protected void MainAction(MouseEvent e) {
        if (AlertMsg.msgConfirmLogout( "Confimar Logout", "Deseja sair para a página de login?")) {
            Main.changedScene("main");
        }
    }
    @FXML
    protected void HomeAction(MouseEvent e) {
        Main.changedScene("home");
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
    private TableColumn tcFuncId;
    @FXML
    private TableColumn tcFuncNome;
    @FXML
    private TableColumn tcFuncSobrenome;
    @FXML
    private TableColumn tcFuncCargo;
    @FXML
    private TableColumn tcFuncCpf;
    @FXML
    private TableColumn tcFuncSalario;
    @FXML
    private TableColumn tcUser;
    @FXML
    private TableColumn tcPass;
    @FXML
    private TableView tbFunc;
    @FXML
    private TextField tfSearch;

    @FXML
    private TextField tfNome;
    @FXML
    private TextField tfSobrenome;
    @FXML
    private TextField tfCargo;
    @FXML
    private TextField tfCpf;
    @FXML
    private TextField tfSalario;
    @FXML
    private TextField tfId;
    @FXML
    private TextField tfUser;
    @FXML
    private TextField tfPass;


    public void tabelafuncionarios()throws SQLException{
        List<FuncionarioTable> funcionarios = new ArrayList<>();

        String consultaSQLfuc = "SELECT * FROM funcionarios";
        Statement statement = connection.createStatement();
        ResultSet resultado = statement.executeQuery(consultaSQLfuc);

        while (resultado.next()) {
            int valorDaColuna1 = resultado.getInt("id");
            String valorDaColuna2 = resultado.getString("nome");
            String valorDaColuna3 = resultado.getString("sobrenome");
            String valorDaColuna4 = resultado.getString("usuario");
            String valorDaColuna5 = resultado.getString("cargo");
            String valorDaColuna6 = resultado.getString("cpf");
            Float valorDaColuna7 = resultado.getFloat("salario");
            String valorDaColuna8 = resultado.getString("senha");

            FuncionarioTable funcionario = new FuncionarioTable(valorDaColuna1, valorDaColuna2, valorDaColuna3, valorDaColuna4, valorDaColuna5, valorDaColuna6, valorDaColuna7, valorDaColuna8);
            funcionarios.add(funcionario);
        }

        ObservableList<FuncionarioTable> Funcionario = FXCollections.observableList(funcionarios);

        tcFuncId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcFuncNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tcFuncSobrenome.setCellValueFactory(new PropertyValueFactory<>("sobrenome"));
        tcUser.setCellValueFactory(new PropertyValueFactory<>("user"));
        tcFuncCargo.setCellValueFactory(new PropertyValueFactory<>("cargo"));
        tcFuncCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        tcFuncSalario.setCellValueFactory(new PropertyValueFactory<>("salario"));
        tcPass.setCellValueFactory(new PropertyValueFactory<>("pass"));

        FilteredList<FuncionarioTable> filteredLis = new FilteredList<>(Funcionario, b -> true);

        tfSearch.textProperty().addListener((observable, oldValue, newValue) ->{
            filteredLis.setPredicate(funcionarioTable -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null){
                    return true;
                }

                String searchKeyowrds = newValue.toLowerCase();

                if (funcionarioTable.getNome().toLowerCase().indexOf(searchKeyowrds) > -1) {
                    return true;
                } else if(funcionarioTable.getSobrenome().toLowerCase().indexOf(searchKeyowrds) > -1){
                    return true;
                } else if(funcionarioTable.getCargo().toLowerCase().indexOf(searchKeyowrds) > -1){
                    return true;
                } else if(funcionarioTable.getCpf().toLowerCase().indexOf(searchKeyowrds) > -1) {
                    return true;
                } else if(funcionarioTable.getUser().toLowerCase().indexOf(searchKeyowrds) > -1){
                    return true;
                } else if(funcionarioTable.getPass().toLowerCase().indexOf(searchKeyowrds) > -1){
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<FuncionarioTable> sortedList = new SortedList<>(filteredLis);

        sortedList.comparatorProperty().bind(tbFunc.comparatorProperty());

        tbFunc.setItems(sortedList);
    }

    @FXML
    public void getItemsActionFunc(MouseEvent event) {
        int index;
        index = tbFunc.getSelectionModel().getSelectedIndex();

        if (index <= -1){
            return;
        }

        // fill the TextFields
        tfId.setText(String.valueOf(tcFuncId.getCellData(index)));
        tfNome.setText((String) tcFuncNome.getCellData(index));
        tfSobrenome.setText((String) tcFuncSobrenome.getCellData(index));
        tfCargo.setText((String) tcFuncCargo.getCellData(index));
        tfCpf.setText((String) tcFuncCpf.getCellData(index));
        tfSalario.setText(String.valueOf((Float) tcFuncSalario.getCellData(index)));
        tfUser.setText((String) tcUser.getCellData(index));
        tfPass.setText((String) tcPass.getCellData(index));
    }

    public void clearTextFields(){
        tfId.setText("");tfNome.setText(""); tfSobrenome.setText(""); tfCargo.setText(""); tfSalario.setText(""); tfCpf.setText(""); tfUser.setText(""); tfPass.setText("");
    }

    public void AddFuncionarioInfoAction(javafx.event.ActionEvent event) throws SQLException {
        if (tfNome.getText().isEmpty() && tfSobrenome.getText().isEmpty() && tfCargo.getText().isEmpty() && tfSalario.getText().isEmpty() && tfCpf.getText().isEmpty() && tfUser.getText().isEmpty() && tfPass.getText().isEmpty()) {
            AlertMsg alertMsg = new AlertMsg();
            alertMsg.msgInformation("Certifique-se de preencher todos.");
        } else {
            banco.inserirfuncinario(tfNome.getText(), tfSobrenome.getText(), tfUser.getText(), tfCargo.getText(), tfCpf.getText(), Float.parseFloat(tfSalario.getText()), tfPass.getText());
            clearTextFields();
            tabelafuncionarios();
        }
    }
    public void AlterFuncionarioInfoAction(javafx.event.ActionEvent event) throws SQLException {
        if (tfNome.getText().isEmpty() && tfSobrenome.getText().isEmpty() && tfCargo.getText().isEmpty() && tfSalario.getText().isEmpty() && tfCpf.getText().isEmpty() && tfUser.getText().isEmpty() && tfPass.getText().isEmpty()) {
            AlertMsg alertMsg = new AlertMsg();
            alertMsg.msgInformation("Certifique-se de preencher todos.");
        } else {
            banco.updateFuncionario(tfNome.getText(), tfSobrenome.getText(), tfUser.getText(), tfCargo.getText(), tfCpf.getText(), Float.parseFloat(tfSalario.getText()), tfPass.getText(), Integer.parseInt(tfId.getText()));
            clearTextFields();
            tabelafuncionarios();
        }
    }
    public void RemoveFuncionarioInfoAction(javafx.event.ActionEvent event) throws SQLException {
        if (tfNome.getText().isEmpty() && tfSobrenome.getText().isEmpty() && tfCargo.getText().isEmpty() && tfSalario.getText().isEmpty() && tfCpf.getText().isEmpty() && tfUser.getText().isEmpty() && tfPass.getText().isEmpty()) {
            AlertMsg alertMsg = new AlertMsg();
            alertMsg.msgInformation("Certifique-se de preencher todos.");

        } else if (AlertMsg.msgConfirmLogout("Confirmação de exclusão", "Deseja remover o funcionário " + tfNome.getText() + " do sistema?")) {
            banco.deletarfuncionario(Integer.parseInt(tfId.getText()));
            clearTextFields();
            tabelafuncionarios();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tfId.setDisable(true);

        try {
            tabelafuncionarios();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
