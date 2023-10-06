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

import java.net.URL;
import java.util.ResourceBundle;

public class itemTransição implements Initializable {


    @FXML
    private ComboBox<String> cbEstadoAnterior;

    @FXML
    private ComboBox<String> cbEstadoFinal;

    @FXML
    private HBox hboxEstado;

    @FXML
    private BorderPane root;

    @FXML
    private ToggleButton tbEsquerda;

    @FXML
    private ToggleButton tbDireita;

    private Transicao transicao = null;

    @FXML
    void removeState(ActionEvent event) {

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

    }
}
