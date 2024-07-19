package com.example.dados_entre_telas;

import java.io.Serializable;

public class Jogo implements Serializable {

    private String titulo;
    private String genero;
    private String lancamento;
    private String modos;
    private String produtora;

    public Jogo(String titulo, String genero, String lancamento, String modos, String produtora) {
        this.titulo = titulo;
        this.genero = genero;
        this.lancamento = lancamento;
        this.modos = modos;
        this.produtora = produtora;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getLancamento() {
        return lancamento;
    }

    public void setLancamento(String lancamento) {
        this.lancamento = lancamento;
    }

    public String getModos() {
        return modos;
    }

    public void setModos(String modos) {
        this.modos = modos;
    }

    public String getProdutora() {
        return produtora;
    }

    public void setProdutora(String produtora) {
        this.produtora = produtora;
    }
}
