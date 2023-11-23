package com.db.bank;

import com.warning.alert.AlertMsg;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.sql.*;
import java.util.Scanner;

//Takushi aqui
public class Banco {
    Scanner scanner1 = new Scanner(System.in);
    public static Connection connection  = conexao();
    Statement executar;
    {
        try {
            executar = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static Connection conexao(){
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/farmacia?serverTimezone=America/Sao_Paulo";
        String user = "adm";
        String password = "1234";
        try {
            conn = DriverManager.getConnection(url, user, password);

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
        return conn;
    }
    public void criartabela(){
        try {
            if(!tabelaExiste("medicamentos")) {
                executar.execute("CREATE TABLE medicamentos(id INT NOT NULL AUTO_INCREMENT ,nome VARCHAR(25), quantidade INT, tipo VARCHAR(25), valor FLOAT, PRIMARY KEY(id))");
            }else{
                System.out.println();
            }
        }catch (SQLException e){
            System.out.println(e);
        }
    }
    public static void deletarcliente(String idcli) throws SQLException{
        Connection connection  = conexao();
        String deletecli = ("DELETE FROM cliente WHERE id = ?");
        PreparedStatement preparedStatement = connection.prepareStatement(deletecli);
        preparedStatement.setString(1, idcli);
        preparedStatement.executeUpdate();
    }
    private boolean tabelaExiste(String nomeTabela) throws SQLException {
        // Verificar se a tabela já existe no banco de dados
        try (Connection connection = conexao();
             ResultSet resultSet = connection.getMetaData().getTables(null, null, nomeTabela, null)) {
            return resultSet.next();
        }
    }
    public void autenticar(){
        try {
            if(!tabelaExiste("autenticar")) {
                executar.execute("CREATE TABLE autenticar(id INT NOT NULL AUTO_INCREMENT, usuario VARCHAR(25), password VARCHAR(25), PRIMARY KEY(id))");
            }else{
                System.out.println();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void inseriradm(String user, String pass){
        try{
            String adm = ("INSERT INTO autenticar (usuario, password) VALUES (?, ?)");
            PreparedStatement preparedStatement = connection.prepareStatement(adm);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, pass);

            preparedStatement.executeUpdate();

        }catch(SQLException e){
            System.out.println(e);
        }
    }
    public void tablecliete(){
        try{
            if(!tabelaExiste("cliente")){
                executar.execute("CREATE TABLE cliente(id INT NOT NULL AUTO_INCREMENT, nome VARCHAR(25), sobrenome VARCHAR(25), usuario VARCHAR(25), telefone VARCHAR(30), PRIMARY KEY(id))");
            }else{
                System.out.println();
            }
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    public static boolean verificarusuario(String user){
        boolean existe = false;
        Connection connection  = conexao();
        try{
            String verificar = "SELECT * FROM cliente WHERE usuario = ?";
            try(PreparedStatement verificarexis = connection.prepareStatement(verificar)){
                verificarexis.setString(1, user);
                try(ResultSet verificarresultado = verificarexis.executeQuery()){
                    existe = verificarresultado.next();
                }
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return existe;
    }
    public void inserircliente(String nomecli, String sobrenomecli, String user, String fone){
        try{
            if(verificarusuario(user)) {
                AlertMsg alert = new AlertMsg();
                alert.msgInformation("Erro ao registrar" , "Nome de usuário ja está sendo utilizado, tente novamente.");
            }else{
                String cliente = ("INSERT INTO cliente (nome, sobrenome, usuario, telefone) VALUES(?,?,?,?)");
                PreparedStatement preparedStatement = connection.prepareStatement(cliente);
                preparedStatement.setString(1, nomecli);
                preparedStatement.setString(2, sobrenomecli);
                preparedStatement.setString(3, user);
                preparedStatement.setString(4, fone);

                preparedStatement.executeUpdate();
            }

        }catch(SQLException e){
            System.out.println(e);
        }
    }

    public void updateClient(String nome, String sobrenome, String usuario, String fone, String idcli) throws SQLException {
        String updateQuaryCli = "UPDATE cliente SET nome = ?, sobrenome = ?, usuario = ?, telefone = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(updateQuaryCli);
        preparedStatement.setString(1, nome);
        preparedStatement.setString(2, sobrenome);
        preparedStatement.setString(3, usuario);
        preparedStatement.setString(4, fone);
        preparedStatement.setString(5, idcli);
        preparedStatement.executeUpdate();
    }

    public void inserirmedicamento(String nomMedi, int quantiMedi,String tipoMedi, float valorMedi ){
        try{
            String medi = ("INSERT INTO medicamentos (nome, quantidade, tipo , valor) VALUES(?,?,?,?)");
            PreparedStatement preparedStatement = connection.prepareStatement(medi);
            preparedStatement.setString(1, nomMedi);
            preparedStatement.setInt(2, quantiMedi);
            preparedStatement.setString(3, tipoMedi);
            preparedStatement.setFloat(4, valorMedi);

            preparedStatement.executeUpdate();

        }catch(SQLException e){
            System.out.println(e);
        }
    }

    public static int somarentidadeseliente() {
        try {
            String consultaSQLCliente = "SELECT COUNT(id) AS soma_total FROM cliente";

            try (PreparedStatement statement = connection.prepareStatement(consultaSQLCliente);
                 ResultSet consultaSoma = statement.executeQuery()) {

                if (consultaSoma.next()) {
                    int soma = consultaSoma.getInt("soma_total");
                    return soma;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public static int somarentidadesefuncionarios() {
        try {
            String consultaSQLCliente = "SELECT COUNT(id) AS soma_total FROM funcionarios";

            try (PreparedStatement statement = connection.prepareStatement(consultaSQLCliente);
                 ResultSet consultaSomafu = statement.executeQuery()) {

                if (consultaSomafu.next()) {
                    int somafu = consultaSomafu.getInt("soma_total");
                    return somafu;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public static int somarentidadesmedicamentos() {
        try {
            String consultaSQLCliente = "SELECT COUNT(id) AS soma_total FROM medicamentos";

            try (PreparedStatement statement = connection.prepareStatement(consultaSQLCliente);
                 ResultSet consultaSomamedi = statement.executeQuery()) {

                if (consultaSomamedi.next()) {
                    int somamedi = consultaSomamedi.getInt("soma_total");
                    return somamedi;
                    //System.out.println("Soma total de Medicamentos registrados: " + somamedi);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void tabelafuncionario(){
        try {
            if(!tabelaExiste("funcionarios")) {
                executar.execute("CREATE TABLE funcionarios(id INT NOT NULL AUTO_INCREMENT, nome VARCHAR(25), sobrenome VARCHAR(25), usuario VARCHAR(25), cargo VARCHAR(25), cpf VARCHAR(25), salario FLOAT, senha VARCHAR(25), PRIMARY KEY(id))");
            }else{
                System.out.println();
            }
        }catch (SQLException e){
            System.out.println(e);
        }
    }
    public void inserirfuncinario(String nome, String sobrenome, String user, String cargo, String Cpf, float salario, String senha){
        try{
            if(verificarusuario(user)) {
                System.out.println("O nome de usuario não pode ser utilizado, tente outro.");
            }else{
                String cliente = ("INSERT INTO funcionarios (nome, sobrenome, usuario, cargo, cpf, salario, senha) VALUES(?,?,?,?,?,?,?)");
                PreparedStatement preparedStatement = connection.prepareStatement(cliente);
                preparedStatement.setString(1, nome);
                preparedStatement.setString(2, sobrenome);
                preparedStatement.setString(3, user);
                preparedStatement.setString(4, cargo);
                preparedStatement.setString(5, Cpf);
                preparedStatement.setFloat(6, salario);
                preparedStatement.setString(7, senha);

                preparedStatement.executeUpdate();
            }

        }catch(SQLException e){
            System.out.println(e);
        }
    }
    public void updateFuncionario(String nome, String sobrenome, String user, String cargo, String cpf, float salario, String senha, int idfunc) throws SQLException {
        String updateQuaryCli = "UPDATE funcionarios SET nome = ?, sobrenome = ?, usuario = ?, cargo = ?, cpf = ?, salario = ?, senha = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(updateQuaryCli);
        preparedStatement.setString(1, nome);
        preparedStatement.setString(2, sobrenome);
        preparedStatement.setString(3, user);
        preparedStatement.setString(4, cargo);
        preparedStatement.setString(5, cpf);
        preparedStatement.setFloat(6, salario);
        preparedStatement.setString(7, senha);
        preparedStatement.setInt(8,idfunc);
        preparedStatement.executeUpdate();
    }
    public static void deletarfuncionario(int num) throws SQLException{
        Connection connection  = conexao();
        String delete = ("DELETE FROM funcionarios WHERE id = ?");
        PreparedStatement preparedStatement = connection.prepareStatement(delete);
        preparedStatement.setInt(1, num);

        preparedStatement.executeUpdate();
    }
    public static void deletarmedicamento(int num) throws SQLException{
        Connection connection  = conexao();
        String delete = ("DELETE FROM medicamentos WHERE id = ?");
        PreparedStatement preparedStatement = connection.prepareStatement(delete);
        preparedStatement.setInt(1, num);

        preparedStatement.executeUpdate();
    }
    public void updateMedicamento(String nome, int quantidade, String tipo, float valor, int idfunc) throws SQLException {
        String updateQuaryCli = "UPDATE medicamentos SET nome = ?, quantidade = ?, tipo = ?, valor = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(updateQuaryCli);
        preparedStatement.setString(1, nome);
        preparedStatement.setInt(2, quantidade);
        preparedStatement.setString(3, tipo);
        preparedStatement.setFloat(4, valor);
        preparedStatement.setInt(5,idfunc);
        preparedStatement.executeUpdate();
    }
    public void registro(){
        try {
            if(!tabelaExiste("registros")) {
                executar.execute("CREATE TABLE registros(id INT NOT NULL AUTO_INCREMENT, usuario VARCHAR(25), medicamento VARCHAR(25), quantidade INT, valor FLOAT, data VARCHAR(50), PRIMARY KEY(id))");
            }else{
                System.out.println();
            }
        }catch (SQLException e){
            System.out.println(e);
        }
    }

    public void inseriregistro(String user, String medicamento, int quantidade, float valor, String date) throws SQLException {
        String dados = ("INSERT INTO registros (usuario, medicamento, quantidade, valor, data) VALUES (?, ?, ?,?, ?)");
        PreparedStatement preparedStatement = connection.prepareStatement(dados);
        preparedStatement.setString(1, user);
        preparedStatement.setString(2, medicamento);
        preparedStatement.setInt(3, quantidade);
        preparedStatement.setFloat(4, valor);
        preparedStatement.setString(5, date);

        preparedStatement.executeUpdate();
    }
    public void carrinho(){
        try {
            if(!tabelaExiste("carrinho")) {
                executar.execute("CREATE TABLE carrinho(id INT NOT NULL AUTO_INCREMENT, usuario VARCHAR(25), medicamento VARCHAR(25), quantidade INT, valor FLOAT, PRIMARY KEY(id))");
            }else{
                System.out.println();
            }
        }catch (SQLException e){
            System.out.println(e);
        }
    }
    public void inserircarrinho(String user, String medicamento, int quantidade, float valor) throws SQLException {
        String dados = ("INSERT INTO carrinho (usuario, medicamento, quantidade, valor) VALUES (?, ?, ?,?)");
        PreparedStatement preparedStatement = connection.prepareStatement(dados);
        preparedStatement.setString(1, user);
        preparedStatement.setString(2, medicamento);
        preparedStatement.setInt(3, quantidade);
        preparedStatement.setFloat(4, valor);

        preparedStatement.executeUpdate();
    }
    public void encomendas(){
        try {
            if(!tabelaExiste("encomendas")) {
                executar.execute("CREATE TABLE encomendas(id INT NOT NULL AUTO_INCREMENT, usuario VARCHAR(25), medicamento VARCHAR(25), quantidade INT, valor INT, data VARCHAR(50), telefone VARCHAR(50), status VARCHAR(50), PRIMARY KEY(id))");
            }else{
                System.out.println();
            }
        }catch (SQLException e){
            System.out.println(e);
        }
    }
    public void inserirencomendas(String user, String medicamento, int quantidade, float valor, String data,String fone, String status) throws SQLException {
        String dados = ("INSERT INTO encomendas (usuario, medicamento, quantidade, valor, telefone, data, status) VALUES (?, ?, ?, ?, ?, ?, ?)");
        PreparedStatement preparedStatement = connection.prepareStatement(dados);
        preparedStatement.setString(1, user);
        preparedStatement.setString(2, medicamento);
        preparedStatement.setInt(3, quantidade);
        preparedStatement.setFloat(4, valor);
        preparedStatement.setString(5, fone);
        preparedStatement.setString(6, data);
        preparedStatement.setString(7, status);

        preparedStatement.executeUpdate();
    }
}