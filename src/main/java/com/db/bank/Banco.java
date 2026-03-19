package com.db.bank;

import com.db.repository.AuthRepository;
import com.db.repository.CarrinhoRepository;
import com.db.repository.ClienteRepository;
import com.db.repository.EncomendaRepository;
import com.db.repository.FuncionarioRepository;
import com.db.repository.MedicamentoRepository;
import com.db.repository.RegistroRepository;
import com.warning.alert.AlertMsg;

import javax.swing.*;
import java.sql.*;

//Takushi aqui
public class Banco {

    public static Connection connection  = conexao();
    private static final ClienteRepository clienteRepository = new ClienteRepository();
    private static final FuncionarioRepository funcionarioRepository = new FuncionarioRepository();
    private static final MedicamentoRepository medicamentoRepository = new MedicamentoRepository();
    private static final RegistroRepository registroRepository = new RegistroRepository();
    private static final CarrinhoRepository carrinhoRepository = new CarrinhoRepository();
    private static final EncomendaRepository encomendaRepository = new EncomendaRepository();
    private static final AuthRepository authRepository = new AuthRepository();
    public static Connection conexao(){
        Connection conn = null;
        try {
            // Explicit load improves compatibility in IDE/module executions.
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DatabaseConnection.open();
        } catch (ClassNotFoundException erroDriver) {
            JOptionPane.showMessageDialog(null, "Driver MySQL nao encontrado. Execute Maven para baixar dependencias.");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
        return conn;
    }
    public void criartabela(){
        try {
            if(!tabelaExiste("medicamentos")) {
                try (Connection current = conexao();
                     Statement executar = current.createStatement()) {
                    executar.execute("CREATE TABLE medicamentos(id INT NOT NULL AUTO_INCREMENT ,nome VARCHAR(25), quantidade INT, tipo VARCHAR(25), valor FLOAT, PRIMARY KEY(id))");
                }
            }else{
                System.out.println();
            }
        }catch (SQLException e){
            System.out.println(e);
        }
    }
    public static void deletarcliente(String idcli) throws SQLException{
        clienteRepository.deleteById(idcli);
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
                try (Connection current = conexao();
                     Statement executar = current.createStatement()) {
                    executar.execute("CREATE TABLE autenticar(id INT NOT NULL AUTO_INCREMENT, usuario VARCHAR(25), password VARCHAR(25), PRIMARY KEY(id))");
                }
            }else{
                System.out.println();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void inseriradm(String user, String pass){
        try{
            authRepository.insertAdmin(user, pass);
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    public void tablecliete(){
        try{
            if(!tabelaExiste("cliente")){
                try (Connection current = conexao();
                     Statement executar = current.createStatement()) {
                    executar.execute("CREATE TABLE cliente(id INT NOT NULL AUTO_INCREMENT, nome VARCHAR(25), sobrenome VARCHAR(25), usuario VARCHAR(25), telefone VARCHAR(30), PRIMARY KEY(id))");
                }
            }else{
                System.out.println();
            }
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    public static boolean verificarusuario(String user){
        try{
            return clienteRepository.existsByUsuario(user);
        }catch(SQLException e){
            System.out.println(e);
            return false;
        }
    }
    public void inserircliente(String nomecli, String sobrenomecli, String user, String fone){
        try{
            if(verificarusuario(user)) {
                AlertMsg alert = new AlertMsg();
                alert.msgInformation("Erro ao registrar" , "Nome de usuário ja está sendo utilizado, tente novamente.");
            }else{
                clienteRepository.insert(nomecli, sobrenomecli, user, fone);
            }

        }catch(SQLException e){
            System.out.println(e);
        }
    }

    public void updateClient(String nome, String sobrenome, String usuario, String fone, String idcli) throws SQLException {
        clienteRepository.update(nome, sobrenome, usuario, fone, idcli);
    }

    public void inserirmedicamento(String nomMedi, int quantiMedi,String tipoMedi, float valorMedi ){
        try{
            medicamentoRepository.insert(nomMedi, quantiMedi, tipoMedi, valorMedi);
        }catch(SQLException e){
            System.out.println(e);
        }
    }

    public static int somarentidadeseliente() {
        try {
            return clienteRepository.countAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public static int somarentidadesefuncionarios() {
        try {
            return funcionarioRepository.countAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public static int somarentidadesmedicamentos() {
        try {
            return medicamentoRepository.countAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void tabelafuncionario(){
        try {
            if(!tabelaExiste("funcionarios")) {
                try (Connection current = conexao();
                     Statement executar = current.createStatement()) {
                    executar.execute("CREATE TABLE funcionarios(id INT NOT NULL AUTO_INCREMENT, nome VARCHAR(25), sobrenome VARCHAR(25), usuario VARCHAR(25), cargo VARCHAR(25), cpf VARCHAR(25), salario FLOAT, senha VARCHAR(25), PRIMARY KEY(id))");
                }
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
                funcionarioRepository.insert(nome, sobrenome, user, cargo, Cpf, salario, senha);
            }

        }catch(SQLException e){
            System.out.println(e);
        }
    }
    public void updateFuncionario(String nome, String sobrenome, String user, String cargo, String cpf, float salario, String senha, int idfunc) throws SQLException {
        funcionarioRepository.update(nome, sobrenome, user, cargo, cpf, salario, senha, idfunc);
    }
    public static void deletarfuncionario(int num) throws SQLException{
        funcionarioRepository.deleteById(num);
    }
    public static void deletarmedicamento(int num) throws SQLException{
        medicamentoRepository.deleteById(num);
    }
    public void updateMedicamento(String nome, int quantidade, String tipo, float valor, int idfunc) throws SQLException {
        medicamentoRepository.update(nome, quantidade, tipo, valor, idfunc);
    }
    public void registro(){
        try {
            if(!tabelaExiste("registros")) {
                try (Connection current = conexao();
                     Statement executar = current.createStatement()) {
                    executar.execute("CREATE TABLE registros(id INT NOT NULL AUTO_INCREMENT, usuario VARCHAR(25), medicamento VARCHAR(25), quantidade INT, valor FLOAT, data VARCHAR(50), PRIMARY KEY(id))");
                }
            }else{
                System.out.println();
            }
        }catch (SQLException e){
            System.out.println(e);
        }
    }

    public void inseriregistro(String user, String medicamento, int quantidade, float valor, String date) throws SQLException {
        registroRepository.insert(user, medicamento, quantidade, valor, date);
    }
    public void carrinho(){
        try {
            if(!tabelaExiste("carrinho")) {
                try (Connection current = conexao();
                     Statement executar = current.createStatement()) {
                    executar.execute("CREATE TABLE carrinho(id INT NOT NULL AUTO_INCREMENT, usuario VARCHAR(25), medicamento VARCHAR(25), quantidade INT, valor FLOAT, PRIMARY KEY(id))");
                }
            }else{
                System.out.println();
            }
        }catch (SQLException e){
            System.out.println(e);
        }
    }
    public void inserircarrinho(String user, String medicamento, int quantidade, float valor) throws SQLException {
        carrinhoRepository.insert(user, medicamento, quantidade, valor);
    }
    public void encomendas(){
        try {
            if(!tabelaExiste("encomendas")) {
                try (Connection current = conexao();
                     Statement executar = current.createStatement()) {
                    executar.execute("CREATE TABLE encomendas(id INT NOT NULL AUTO_INCREMENT, usuario VARCHAR(25), medicamento VARCHAR(25), quantidade INT, valor INT, data VARCHAR(50), telefone VARCHAR(50), status VARCHAR(50), PRIMARY KEY(id))");
                }
            }else{
                System.out.println();
            }
        }catch (SQLException e){
            System.out.println(e);
        }
    }
    public void inserirencomendas(String user, String medicamento, int quantidade, float valor, String data,String fone, String status) throws SQLException {
        encomendaRepository.insert(user, medicamento, quantidade, valor, data, fone, status);
    }
}