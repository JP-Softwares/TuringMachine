package com.jp.controle;
import com.jp.modelo.*;

import java.util.Iterator;
import java.util.List;

public class maquina {

    public String testarMaquina(List<Estado> estados, String cadeia){
        int posFita = 0;
        char[] fita = separaCadeia(cadeia);
        Estado estadoAtual = achaInicial(estados);
        while(true){
            char a = fita[posFita];
            //Transicao trans = estadoAtual.getListaTransicao().get(a);
            //EstadoAtual = Transição.próximoEstado;
            return null;
        }
        //return null;
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
