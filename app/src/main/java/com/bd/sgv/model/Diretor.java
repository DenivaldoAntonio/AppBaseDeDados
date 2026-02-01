package com.bd.sgv.model;

public class Diretor{
    private Integer idDiretor;
    private String nome;
    private Boolean hidden;

    public Diretor(){
    }

    public Diretor(Integer idDiretor, String nome, Boolean hidden){
        this.idDiretor = idDiretor; this.nome = nome; this.hidden = hidden;
    }

    public Integer getIdDiretor(){
        return idDiretor;
    }

    public void setIdDiretor(Integer idDiretor){
        this.idDiretor = idDiretor;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public Boolean getHidden(){
        return hidden;
    }

    public void setHidden(Boolean hidden){
        this.hidden = hidden;
    }

    @Override
    public String toString(){
        return "Diretor{" + "idDiretor=" + idDiretor + ", nome='" + nome + '\'' + ", hidden=" + hidden + '}';
    }
}