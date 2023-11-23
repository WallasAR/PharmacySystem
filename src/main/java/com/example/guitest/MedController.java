package com.example.guitest;

import com.db.bank.Banco;
import com.table.view.FuncionarioTable;
import com.table.view.MedicamentoTable;
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

public class MedController implements Initializable {
    Banco banco = new Banco();
    @FXML
    protected void MainAction(MouseEvent e) {
        if (AlertMsg.msgConfirm("Confimar Logout", "Deseja sair para a página de login?")) {
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
    protected void ClientAction(MouseEvent e) {
        Main.changedScene("client");
    }
    @FXML
    protected void RecordAction(MouseEvent e) {
        Main.changedScene("record");
    }


    @FXML
    private TableView tbMedicamento;
    @FXML
    private TableColumn clIdmedi;
    @FXML
    private TableColumn clNomemedi;
    @FXML
    private TableColumn clQuantimedi;
    @FXML
    private TableColumn clTipomedi;
    @FXML
    private TableColumn clPreçomedi;
    @FXML
    private TextField tfSearch;

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

        clIdmedi.setCellValueFactory(new PropertyValueFactory<>("id"));
        clNomemedi.setCellValueFactory(new PropertyValueFactory<>("nomemedi"));
        clQuantimedi.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        clTipomedi.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        clPreçomedi.setCellValueFactory(new PropertyValueFactory<>("valor"));

        tbMedicamento.setItems(datamedi);


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

        sortedList.comparatorProperty().bind(tbMedicamento.comparatorProperty());

        tbMedicamento.setItems(sortedList);
    }

    public void getItemsActionMedi(MouseEvent event) {
        int index;
        index = tbMedicamento.getSelectionModel().getSelectedIndex();

        if (index <= -1){
            return;
        }

        // fill the TextFields
        tfId.setText(String.valueOf(clIdmedi.getCellData(index)));
        tfNome.setText((String) clNomemedi.getCellData(index));
        tfQuantidade.setText(String.valueOf((int) clQuantimedi.getCellData(index)));
        tfTipo.setText((String) clTipomedi.getCellData(index));
        tfValor.setText(String.valueOf((Float) clPreçomedi.getCellData(index)));
    }

    public void clearTextFields(){
        tfId.setText("");tfNome.setText(""); tfQuantidade.setText(""); tfTipo.setText(""); tfValor.setText("");
    }

    public void AddMedInfoAction(javafx.event.ActionEvent event) throws SQLException {
        if (tfId.getText().isEmpty() && tfNome.getText().isEmpty() && tfQuantidade.getText().isEmpty() && tfTipo.getText().isEmpty() && tfValor.getText().isEmpty()) {
            AlertMsg alertMsg = new AlertMsg();
            alertMsg.msgInformation("Ops! Algo nos campos não parece certo","Certifique-se de preencher todos.");
        } else {
            banco.inserirmedicamento(tfNome.getText(), Integer.parseInt(tfQuantidade.getText()), tfTipo.getText(), Float.parseFloat(tfValor.getText()));
            clearTextFields();
            tabelamedi();
        }
    }
    public void AlterMedInfoAction(javafx.event.ActionEvent event) throws SQLException {
        if (tfId.getText().isEmpty() && tfNome.getText().isEmpty() && tfQuantidade.getText().isEmpty() && tfTipo.getText().isEmpty() && tfValor.getText().isEmpty()) {
            AlertMsg alertMsg = new AlertMsg();
            alertMsg.msgInformation("Ops! Algo nos campos não parece certo","Certifique-se de preencher todos.");
        } else {
            banco.updateMedicamento(tfNome.getText(), Integer.parseInt(tfQuantidade.getText()), tfTipo.getText(), Float.parseFloat(tfValor.getText()), Integer.parseInt(tfId.getText()));
            clearTextFields();
            tabelamedi();
        }
    }
    public void RemoveMedInfoAction(javafx.event.ActionEvent event) throws SQLException{
        if (tfId.getText().isEmpty() && tfNome.getText().isEmpty() && tfQuantidade.getText().isEmpty() && tfTipo.getText().isEmpty() && tfValor.getText().isEmpty()) {
            AlertMsg alertMsg = new AlertMsg();
            alertMsg.msgInformation("Ops! Algo nos campos não parece certo","Certifique-se de preencher todos.");
        } else if (AlertMsg.msgConfirm("Confirmação de exclusão", "Deseja remover o medicamento " + tfNome.getText() + " do sistema?")) {
            banco.deletarmedicamento(Integer.parseInt(tfId.getText()));
            clearTextFields();
            tabelamedi();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tfId.setDisable(true);

        try {
            tabelamedi();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
