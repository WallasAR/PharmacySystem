package com.table.view;

public class ClienteTable {
    public int idcli;
    public String nomecli;
    public String sobrenome;
    public String usuario;
    public String fone;

    public ClienteTable(int idcli, String nomecli, String sobrenome, String usuario, String fone) {
        this.idcli = idcli;
        this.nomecli = nomecli;
        this.sobrenome = sobrenome;
        this.usuario = usuario;
        this.fone = fone;
    }

    public int getIdcli() {
        return idcli;
    }

    public void setIdcli(int idcli) {
        this.idcli = idcli;
    }

    public String getNomecli() {
        return nomecli;
    }

    public void setNomecli(String nomecli) {
        this.nomecli = nomecli;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String Fone) {
        this.fone = fone;
    }
}
