package com.jp.visao;

import atlantafx.base.controls.Popover;
import com.jp.modelo.Estado;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class TelaHome implements Initializable {

    @FXML
    private BorderPane bpAddState;

    @FXML
    private HBox hboxTeste;

    @FXML
    private AnchorPane paneRoot;

    @FXML
    void OpenAddState(ActionEvent event) {
        bpAddState.setVisible(true);
    }

    @FXML
    void addState(ActionEvent event) {
        exitAddState(null);
    }

    @FXML
    void exitAddState(ActionEvent event) {
        bpAddState.setVisible(false);

    }

    private SwitchButton switchButton;

    private List<Estado> estados = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Run.telaHome = this;
        switchButton = new SwitchButton();

        estados = new LinkedList<>();

        estados.add(new Estado("aceita", false, null));
        estados.add(new Estado("rejeita", false, null));

       // hboxTeste.getChildren().add(0, switchButton);
    }
}
