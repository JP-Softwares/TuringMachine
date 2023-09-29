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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class TelaHome implements Initializable {

    @FXML
    private AnchorPane paneRoot;

    private List<Estado> estados = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Run.telaHome = this;

        estados = new LinkedList<>();

        estados.add(new Estado("aceita", false, null));
        estados.add(new Estado("rejeita", false, null));
    }
}
