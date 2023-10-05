package com.jp.controle;
import com.jp.modelo.*;

import java.util.Iterator;
import java.util.List;

public class maquina {
    int posFita = 0;
    int tamanho = 0;
    char[] fita = null;

    public String testarMaquina(List<Estado> estados, String cadeia){
        fita = separaCadeia(cadeia);
        tamanho = fita.length-1;
        Estado estadoAtual = achaInicial(estados);
        if(estadoAtual.getNome().equals("aceita"))return "aceita";
        if(estadoAtual.getNome().equals("rejeita"))return "rejeita";
        while(true){
            char simbolo = fita[posFita];
            Transicao trans = estadoAtual.getListaTransicao().get(simbolo);
            if(trans == null) return "rejeita";
            estadoAtual = trans.getProxEstado();
            if(estadoAtual.getNome().equals("aceita"))return "aceita";
            if(estadoAtual.getNome().equals("rejeita")) return "rejeita";
            if(trans.isMarcaX()) fita[posFita]= 'X';
            moveFita(trans.getDirecao());
        }
        //return null;
    }



    private void moveFita(char direcao){
        if(direcao == 'E'){
            if(posFita > 0){
                posFita--;
            }
        }else{
            if(posFita == tamanho){
                int i = 0;
                char[] fita1 = new char[tamanho+2];
                for ( char indice: fita) {
                    fita1[i] = fita[i];
                    i++;
                }
                fita1[i] = ' ';
            }
            posFita++;
        }
    }


    private Estado achaInicial(List<Estado> estados){
        for (Estado cidade: estados) {
            if(cidade.isEstadoInicial()){
                return cidade;
            }
        }
        return null;
    }


    private char[] separaCadeia(String cadeia){
        char[] fita = cadeia.toCharArray();
        return fita;
    }
}
