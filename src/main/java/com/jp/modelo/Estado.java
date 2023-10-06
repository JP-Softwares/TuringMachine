package com.jp.modelo;
import java.util.HashMap;

public class Estado {
    private String nome = "";
    private boolean estadoInicial = false;
    private HashMap<Character, Transicao> ListaTransicao = null;

    public Estado( String nome, boolean estadoInicial, HashMap<Character, Transicao> ListaTransicao){
        this.nome = nome;
        this.estadoInicial = estadoInicial;
        this.ListaTransicao = ListaTransicao;
    }

    public Estado(String nome, boolean estadoInicial){
        this.nome = nome;
        this.estadoInicial = estadoInicial;
        this.ListaTransicao = new HashMap<>();
    }

    public Estado(){
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isEstadoInicial() {
        return estadoInicial;
    }

    public void setEstadoInicial(boolean estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

    public HashMap<Character, Transicao> getListaTransicao() {
        return ListaTransicao;
    }

    public void setListaTransicao(HashMap<Character, Transicao> listaTransicao) {
        ListaTransicao = listaTransicao;
    }
}
