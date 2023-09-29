package com.jp.modelo;

public class Transicao {
    private char direcao;

    private boolean marcaX;

    private Estado proxEstado;


    public Transicao(char direcao, boolean marcaX, Estado proxEstado) {
        this.direcao = direcao;
        this.marcaX = marcaX;
        this.proxEstado = proxEstado;
    }

    public char getDirecao() {
        return direcao;
    }

    public void setDirecao(char direcao) {
        this.direcao = direcao;
    }

    public boolean isMarcaX() {
        return marcaX;
    }

    public void setMarcaX(boolean marcaX) {
        this.marcaX = marcaX;
    }

    public Estado getProxEstado() {
        return proxEstado;
    }

    public void setProxEstado(Estado proxEstado) {
        this.proxEstado = proxEstado;
    }
}

    //Transição (direção, marcaUmX?, próximo Estado)