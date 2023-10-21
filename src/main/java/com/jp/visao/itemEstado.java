package com.jp.visao;

import com.jp.modelo.Estado;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class itemEstado implements Initializable {

    @FXML
    private VBox vboxEstado;

    @FXML
    private BorderPane root;

    @FXML
    private TextField tfNome;

    private Estado estado = null;

    private SwitchButton switchButton = null;

    @FXML
    void nameChange(KeyEvent event) {
        if (!tfNome.getText().isEmpty()) this.estado.setNome(tfNome.getText());
    }

    @FXML
    void removeState(ActionEvent event) {
        Run.telaHome.removeState(this.estado, this.root);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        switchButton = new SwitchButton();
        vboxEstado.getChildren().add(switchButton);

        this.estado = Run.telaHome.ultimoEstadoAdicionado;

        switchButton.setState(this.estado.isEstadoInicial());
        switchButton.setOnChange(event -> {
            this.estado.setEstadoInicial(switchButton.getState());
        });

        tfNome.setText(this.estado.getNome());
    }
}
