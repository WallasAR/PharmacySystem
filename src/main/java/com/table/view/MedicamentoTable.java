package com.table.view;

public class MedicamentoTable {
    private int id;
    private String nomemedi;
    private int quantidade;
    private String tipo;
    private float valor;

    public MedicamentoTable(int id, String nomemedi, int quantidade, String tipo, float valor) {
        this.id = id;
        this.nomemedi = nomemedi;
        this.quantidade = quantidade;
        this.tipo = tipo;
        this.valor = valor;
    }

    public String getNomemedi() {
        return nomemedi;
    }

    public void setNomemedi(String nomemedi) {
        this.nomemedi = nomemedi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
}
