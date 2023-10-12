package com.jp.visao;

import com.jp.modelo.Estado;
import com.jp.modelo.Transicao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

public class itemTransição implements Initializable {


    @FXML
    private ComboBox<Estado> cbEstadoAnterior;

    @FXML
    private ComboBox<Estado> cbEstadoFinal;

    @FXML
    private BorderPane root;

    @FXML
    private ToggleButton tbDireita;

    @FXML
    private ToggleButton tbEsquerda;

    @FXML
    private TextField tfLetraTransicao;

    @FXML
    private VBox vboxTransicao;

    private Transicao transicao = null;
    private Estado estado = null;

    private SwitchButton switchButton = null;

    private char letraTransicao = ' ';

    @FXML
    void removeTransition(ActionEvent event) {
        Run.telaHome.removeTransition(this.estado, this.letraTransicao, this.root);
    }

    @FXML
    void setDireita(ActionEvent event) {
        this.transicao.setDirecao('D');
        tbEsquerda.setSelected(false);
    }

    @FXML
    void setEsquerda(ActionEvent event) {
        this.transicao.setDirecao('E');
        tbDireita.setSelected(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        switchButton = new SwitchButton();
        vboxTransicao.getChildren().add(switchButton);

        this.transicao = Run.telaHome.ultimaTransicaoAdicionada;
        this.estado = Run.telaHome.estadoUltimaTransicaoAdicionada;

        switchButton.setState(this.transicao.isMarcaX());
        switchButton.setOnChange(event -> {
            this.transicao.setMarcaX(switchButton.getState());
        });

        cbEstadoAnterior.setConverter(Run.telaHome.conversorEstadoString);
        cbEstadoFinal.setConverter(Run.telaHome.conversorEstadoString);

        cbEstadoAnterior.setItems(Run.telaHome.estados.filtered(estado -> !estado.getNome().equals("aceita") && !estado.getNome().equals("rejeita")));
        cbEstadoFinal.setItems(Run.telaHome.estados);

        cbEstadoAnterior.getSelectionModel().select(this.estado);
        cbEstadoFinal.getSelectionModel().select(this.transicao.getProxEstado());

        switch (transicao.getDirecao()) {
            case 'D':
                tbDireita.setSelected(true);
                break;
            case 'E':
                tbEsquerda.setSelected(true);
                break;
        }

        for (Character keyLetra : this.estado.getListaTransicao().keySet()) {
            if (this.estado.getListaTransicao().get(keyLetra) == this.transicao) {
                letraTransicao = keyLetra;
                break;
            }
        }

        tfLetraTransicao.setText(letraTransicao + "");

    }
}
