package com.table.view;


public class FuncionarioTable {
    private int id;
    private String nome;
    private String sobrenome;
    private String user;
    private String cargo;
    private String cpf;
    private float salario;
    private String pass;

    public FuncionarioTable(int id, String nome, String sobrenome, String user, String cargo, String cpf, float salario, String pass) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.user = user;
        this.cargo = cargo;
        this.cpf = cpf;
        this.salario = salario;
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        cpf = cpf;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
