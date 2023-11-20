package com.table.view;

public class
PedidosTable {
    private int idpedido;
    private String nomecli;
    private String nomemedipedido;
    private int quantidademedipedido;
    private String tipomedi;
    private float valorpedido;

    public PedidosTable(int idpedido, String nomecli, String nomemedipedido, int quantidademedipedido, String tipomedi, float valorpedido) {
        this.idpedido = idpedido;
        this.nomecli = nomecli;
        this.nomemedipedido = nomemedipedido;
        this.quantidademedipedido = quantidademedipedido;
        this.tipomedi = tipomedi;
        this.valorpedido = valorpedido;
    }

    public int getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(int idpedido) {
        this.idpedido = idpedido;
    }

    public String getNomecli() {
        return nomecli;
    }

    public void setNomecli(String nomecli) {
        this.nomecli = nomecli;
    }

    public String getNomemedipedido() {
        return nomemedipedido;
    }

    public void setNomemedipedido(String nomemedipedido) {
        this.nomemedipedido = nomemedipedido;
    }

    public int getQuantidademedipedido() {
        return quantidademedipedido;
    }

    public void setQuantidademedi(int quantidademedipedido) {
        this.quantidademedipedido = quantidademedipedido;
    }

    public String getTipomedi() {
        return tipomedi;
    }

    public void setTipomedi(String tipomedi) {
        this.tipomedi = tipomedi;
    }

    public float getValorpedido() {
        return valorpedido;
    }

    public void setValorpedido(float valorpedido) {
        this.valorpedido = valorpedido;
    }
}
