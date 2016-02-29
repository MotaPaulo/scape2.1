package com.scape.ufv.scape.Bases;


public class TipoInscricao {
    private int id;
    private String inscricao;

    public TipoInscricao(int id, String inscricao){
        this.setId(id);
        this.setInscricao(inscricao);
    }

    public String getInscricao() {
        return inscricao;
    }
    public void setInscricao(String inscricao) {
        this.inscricao = inscricao;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}

