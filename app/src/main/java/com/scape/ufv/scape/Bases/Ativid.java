package com.scape.ufv.scape.Bases;

import java.io.Serializable;

public class Ativid implements Serializable{
    private int id;
    private String nome;
    private String tema;
    private String data;
    private String hora_in;
    private String hora_fim;
    private String descricao;
    private String id_evento;


    public Ativid (int id,String nome,String tema,String data, String horai,String horaf, String descricao, String idevento){
        this.setId(id);
        this.setNome(nome);
        this.setData(data);
        this.setTema(tema);
        this.setHora_in(horai);
        this.setHora_fim(horaf);
        this.setDescricao(descricao);
        this.setId_evento(idevento);
    }

    public Ativid (String nome,String tema,String data, String horai,String horaf, String descricao, String idevento){
        this.setNome(nome);
        this.setData(data);
        this.setTema(tema);
        this.setHora_in(horai);
        this.setHora_fim(horaf);
        this.setDescricao(descricao);
        this.setId_evento(idevento);
    }
    public Ativid (String nome,String tema,String data, String horai,String horaf, String descricao){
        this.setNome(nome);
        this.setData(data);
        this.setTema(tema);
        this.setHora_in(horai);
        this.setHora_fim(horaf);
        this.setDescricao(descricao);

    }


    public Ativid() {
        // TODO Auto-generated constructor stub
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora_in() {
        return hora_in;
    }

    public void setHora_in(String hora_in) {
        this.hora_in = hora_in;
    }

    public String getHora_fim() {
        return hora_fim;
    }

    public void setHora_fim(String hora_fim) {
        this.hora_fim = hora_fim;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getId_evento() {
        return id_evento;
    }

    public void setId_evento(String id_evento) {
        this.id_evento = id_evento;
    }

}
