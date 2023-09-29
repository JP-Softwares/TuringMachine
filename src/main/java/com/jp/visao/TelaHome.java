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

    public void addScreen(String fxml){
        try{
            if(paneRoot.getChildren().size() > 0) paneRoot.getChildren().remove(0);

            paneRoot.getChildren().add(FXMLLoader.load(getClass().getResource(fxml)));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void createState(ActionEvent event) {
        addScreen("");

        Point posicaoMouse = MouseInfo.getPointerInfo().getLocation();
        System.out.println(posicaoMouse.x + " - " + posicaoMouse.y);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Run.telaHome = this;

        estados = new LinkedList<>();
    }
}
