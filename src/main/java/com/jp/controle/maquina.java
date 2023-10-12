package com.jp.controle;
import com.jp.modelo.*;

import java.util.Iterator;
import java.util.List;

public class maquina {
    int posFita = 0;
    int tamanho = 0;
    char[] fita = null;

    public String testarMaquina(List<Estado> estados, String cadeia){
        posFita = 0;
        fita = separaCadeia(cadeia);
        tamanho = fita.length-1;
        Estado estadoAtual = achaInicial(estados);
        if(estadoAtual.getNome().equals("aceita"))return "aceita";
        if(estadoAtual.getNome().equals("rejeita"))return "rejeita";
        while(true){
            char simbolo = fita[posFita];
            System.out.println("simbolo atual: "+simbolo);
            Transicao trans = estadoAtual.getListaTransicao().get(simbolo);
            System.out.println("Estado atual : " + estadoAtual.getNome());
            System.out.println("Transições: ");
            for (char simbolo1 :estadoAtual.getListaTransicao().keySet()
            ) {
                System.out.print(simbolo1 + "|");
            }
            System.out.println();
            if(trans == null) {

                System.out.println("Rejeitei aqui - trans = null");
                return "rejeita";
            }
            estadoAtual = trans.getProxEstado();
            if(estadoAtual.getNome().equals("aceita")) {
                System.out.println("aceitei aqui - estado = aceita");
                return "aceita";
            }
            if(estadoAtual.getNome().equals("rejeita")) {
                System.out.println("Rejeitei aqui - Estado == rejeita");
                return "rejeita";
            }
            if(trans.isMarcaX()) fita[posFita]= 'x';
            moveFita(trans.getDirecao());
            System.out.println("----------------------------------- \n\n\n");
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
                fita = fita1;
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
