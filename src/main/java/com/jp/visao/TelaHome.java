package com.jp.visao;

import com.jp.modelo.Estado;
import com.jp.modelo.Transicao;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class TelaHome implements Initializable {

    @FXML
    private BorderPane bpAddState;

    @FXML
    private BorderPane bpAddTransition;

    @FXML
    private ComboBox<String> cbEstadoAnterior;

    @FXML
    private ComboBox<String> cbEstadoFinal;

    @FXML
    private VBox vboxEstado;

    @FXML
    private ToggleButton tbDireita;

    @FXML
    private ToggleButton tbEsquerda;

    @FXML
    private VBox vboxListaEstados;

    @FXML
    private VBox vboxTransicao;

    @FXML
    private VBox vboxListaTransicoes;

    @FXML
    private AnchorPane paneRoot;

    @FXML
    private TextField tfLetraTransicao;

    @FXML
    private TextField tfNomeEstado;

    public Estado ultimoEstadoAdicionado = null;

    public Transicao ultimaTransicaoAdicionada = null;

    private SwitchButton sbState;

    private SwitchButton sbTransition;

    private List<Estado> estados = null;

    private Estado getEstado(String nomeEstado) {
        return estados.stream().filter(estado -> estado.getNome().equals(nomeEstado)).findFirst().get();
    }

    @FXML
    void OpenAddState(ActionEvent event) {
        bpAddState.setVisible(true);
        sbState.setState(estados.stream().filter(estado -> !estado.getNome().equals("aceita") && !estado.getNome().equals("rejeita")).toList().isEmpty());
    }

    @FXML
    void addState(ActionEvent event) {
        exitAddState(null);
        if (!tfNomeEstado.getText().equals("aceita") && !tfNomeEstado.getText().equals("rejeita")){
            if (estados.stream().filter(estado -> estado.getNome().equals(tfNomeEstado.getText())).toArray().length == 0) {
                ultimoEstadoAdicionado = new Estado(tfNomeEstado.getText(), sbState.getState());
                estados.add(ultimoEstadoAdicionado);
                vboxListaEstados.getChildren().add(Run.app.getScene("itemEstado"));

                tfNomeEstado.setText("");

                cbEstadoAnterior.getItems().removeAll();


                cbEstadoAnterior.getItems().addAll(estados.stream().filter(estado -> !estado.getNome().equals("aceita") && !estado.getNome().equals("rejeita")).map(estado -> estado.getNome()).toList());

                cbEstadoFinal.getItems().removeAll();

                cbEstadoFinal.getItems().addAll(estados.stream().map(estado -> estado.getNome()).toList());
            }
        }
    }

    public void removeState(Estado estado, Node item){
        estados.remove(estado);
        vboxListaEstados.getChildren().remove(item);
    }

    @FXML
    void exitAddState(ActionEvent event) {
        bpAddState.setVisible(false);

    }

    @FXML
    void OpenAddTransition(ActionEvent event) {
        bpAddTransition.setVisible(true);
    }

    @FXML
    void addTransition(ActionEvent event) {
        cbEstadoFinal.getSelectionModel().getSelectedItem();
        ultimaTransicaoAdicionada = new Transicao(tbEsquerda.isSelected() ? 'E' : tbDireita.isSelected() ? 'D' : ' ', sbTransition.getState(), getEstado(cbEstadoFinal.getSelectionModel().getSelectedItem()));

        getEstado(cbEstadoAnterior.getSelectionModel().getSelectedItem()).getListaTransicao().put(tfLetraTransicao.getText().toCharArray()[0], ultimaTransicaoAdicionada);

        vboxListaTransicoes.getChildren().add(Run.app.getScene("itemTransição"));
    }

    @FXML
    void exitAddTransition(ActionEvent event) {
        bpAddTransition.setVisible(false);
    }

    @FXML
    void setDireita(ActionEvent event) {
        tbEsquerda.setSelected(false);
    }

    @FXML
    void setEsquerda(ActionEvent event) {
        tbDireita.setSelected(false);
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Run.telaHome = this;
        sbState = new SwitchButton();
        vboxEstado.getChildren().add(sbState);

        sbTransition = new SwitchButton();
        vboxTransicao.getChildren().add(sbTransition);

        estados = new LinkedList<>();

        estados.add(new Estado("aceita", false, null));
        estados.add(new Estado("rejeita", false, null));
    }
}
