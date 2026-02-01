package com.bd.sgv.model;

public class Ator{
    private Integer idAtor;
    private String nome;
    private Character sexo;

    public Ator(){
    }

    public Ator(Integer idAtor, String nome, Character sexo){
        this.idAtor = idAtor; this.nome = nome; this.sexo = sexo;
    }

    public Integer getIdAtor(){
        return idAtor;
    }

    public void setIdAtor(Integer idAtor){
        this.idAtor = idAtor;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public Character getSexo(){
        return sexo;
    }

    public void setSexo(Character sexo){
        this.sexo = sexo;
    }

    @Override
    public String toString(){
        return "Ator{" + "idAtor=" + idAtor + ", nome='" + nome + '\'' + ", sexo=" + sexo + '}';
    }
}