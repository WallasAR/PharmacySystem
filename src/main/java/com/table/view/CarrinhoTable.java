package com.table.view;

public class CarrinhoTable {
    private int id;
    private String usuario;
    private String nomeMed;
    private int quantidade;
    private float valor;

    public CarrinhoTable(int id, String usuario, String nomeMed, int quantidade, float valor) {
        this.id = id;
        this.usuario = usuario;
        this.nomeMed = nomeMed;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNomeMed() {
        return nomeMed;
    }

    public void setNomeMed(String nomeMed) {
        this.nomeMed = nomeMed;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
}
