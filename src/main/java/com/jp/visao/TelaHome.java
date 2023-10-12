package com.jp.visao;

import com.jp.controle.maquina;
import com.jp.modelo.Estado;
import com.jp.modelo.Transicao;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.util.StringConverter;

import javax.swing.*;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class TelaHome implements Initializable {

    @FXML
    private BorderPane bpAddState;

    @FXML
    private BorderPane bpAddTransition;

    @FXML
    private ComboBox<Estado> cbEstadoAnterior;

    @FXML
    private ComboBox<Estado> cbEstadoFinal;

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

    @FXML
    private TextField tfCadeia;


    public Estado ultimoEstadoAdicionado = null;

    public Estado estadoUltimaTransicaoAdicionada = null;

    public Transicao ultimaTransicaoAdicionada = null;

    private SwitchButton sbState;

    private SwitchButton sbTransition;

    public ObservableList<Estado> estados = null;

    public StringConverter<Estado> conversorEstadoString = null;

    private maquina turingMachine = null;

    public Estado getEstado(String nomeEstado) {
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
            }
        }
    }

    public void removeState(Estado estado, Node item){
        estados.remove(estado);
        vboxListaEstados.getChildren().remove(item);
    }

    public void changeEstadoTransition(Estado estadoAnterior, Estado estadoNovo, char letraTransicao, Transicao transicao){
        getEstado(estadoAnterior.getNome()).getListaTransicao().remove(letraTransicao);
        getEstado(estadoNovo.getNome()).getListaTransicao().put(letraTransicao, transicao);
    }

    public void changeLetterTransition(Estado estado, char letraTransicaoAntiga, char letraTransicaoNova, Transicao transicao){
        getEstado(estado.getNome()).getListaTransicao().remove(letraTransicaoAntiga);
        getEstado(estado.getNome()).getListaTransicao().put(letraTransicaoNova, transicao);
    }

    public void removeTransition(Estado estado, char letraTransicao, Node item){
        getEstado(estado.getNome()).getListaTransicao().remove(letraTransicao);
        vboxListaTransicoes.getChildren().remove(item);
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
        ultimaTransicaoAdicionada = new Transicao(tbEsquerda.isSelected() ? 'E' : tbDireita.isSelected() ? 'D' : ' ', sbTransition.getState(), getEstado(cbEstadoFinal.getSelectionModel().getSelectedItem().getNome()));

        estadoUltimaTransicaoAdicionada = getEstado(cbEstadoAnterior.getSelectionModel().getSelectedItem().getNome());

        getEstado(cbEstadoAnterior.getSelectionModel().getSelectedItem().getNome()).getListaTransicao().put(tfLetraTransicao.getText().toCharArray()[0], ultimaTransicaoAdicionada);

        vboxListaTransicoes.getChildren().add(Run.app.getScene("itemTransição"));

        exitAddTransition(null);
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


    @FXML
    void playTuringMachine(ActionEvent event) {
        JOptionPane.showMessageDialog(null, "Resultado: " + turingMachine.testarMaquina(this.estados, tfCadeia.getText()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        estados = FXCollections.observableArrayList();

        Run.telaHome = this;
        sbState = new SwitchButton();
        vboxEstado.getChildren().add(sbState);

        sbTransition = new SwitchButton();
        vboxTransicao.getChildren().add(sbTransition);

        //estados = new LinkedList<>();
        turingMachine = new maquina();


        estados.add(new Estado("aceita", false, null));
        estados.add(new Estado("rejeita", false, null));

        conversorEstadoString = new StringConverter<Estado>() {
            @Override
            public String toString(Estado estado) {
                return estado == null ? "" : estado.getNome();
            }

            @Override
            public Estado fromString(String s) {
                return getEstado(s);
            }
        };

        cbEstadoAnterior.setConverter(conversorEstadoString);
        cbEstadoFinal.setConverter(conversorEstadoString);

        cbEstadoAnterior.setItems(estados.filtered(estado -> !estado.getNome().equals("aceita") && !estado.getNome().equals("rejeita")));
        //cbEstadoAnterior.setItems(estados.stream().filter(estado -> !estado.getNome().equals("aceita") && !estado.getNome().equals("rejeita")).map(estado -> estado.getNome()).toList());
        cbEstadoFinal.setItems(estados);

        //cbEstadoFinal.setItems((ObservableList<String>) estados.stream().map(estado -> estado.getNome()).toList());
    }
}
