package com.scape.ufv.scape.Bases;

import java.io.Serializable;

public class Participante implements Serializable {
    private int id;
    private String nome;
    private String email;
    private String instituicao;
    private String evento;
    private String inscricao;
    private String senha;

    public Participante(int id,String nome,String inscricao, String evento, String email, String instituicao,String senha){
        this.setId(id);
        this.setNome(nome);
        this.setInscricao(inscricao);
        this.setEvento(evento);
        this.setEmail(email);
        this.setInstituicao(instituicao);
        this.setSenha(senha);
    }
    public Participante(String nome,String inscricao, String evento, String email, String instituicao,String senha){

        this.setNome(nome);
        this.setInscricao(inscricao);
        this.setEvento(evento);
        this.setEmail(email);
        this.setInstituicao(instituicao);
        this.setSenha(senha);
    }
    public Participante(){

    }
    public String getEvento() {
        return evento;
    }
    public void setEvento(String evento) {
        this.evento = evento;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getInstituicao() {
        return instituicao;
    }
    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }
    public String getInscricao() {
        return inscricao;
    }
    public void setInscricao(String inscricao) {
        this.inscricao = inscricao;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }


}
