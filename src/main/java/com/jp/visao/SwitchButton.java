package com.jp.visao;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class SwitchButton extends StackPane {
    private final Rectangle back = new Rectangle(30, 10, Color.RED);
    private final Button button = new Button();
    private String buttonStyleOff = "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 0.2, 0.0, 0.0, 2); -fx-background-color: WHITE;";
    private String buttonStyleOn = "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 0.2, 0.0, 0.0, 2); -fx-background-color: #00893d;";
    private boolean state;

    EventHandler<Event> click;

    EventHandler<Event> onChange;

    public void setOnChange(EventHandler<Event> onChange){
        this.onChange = onChange;
    }

    private void init() {
        getChildren().addAll(back, button);
        setMinSize(30, 10);
        setMaxSize(60, 30);
        back.maxWidth(60);
        back.minWidth(60);
        back.maxHeight(10);
        back.minHeight(10);
        back.setArcHeight(back.getHeight());
        back.setArcWidth(back.getHeight());
        back.setFill(Color.valueOf("#ced5da"));
        Double r = 2.0;
        button.setShape(new Circle(r));
        setAlignment(button, Pos.CENTER_LEFT);
        button.setMaxSize(20, 20);
        button.setMinSize(15, 15);
        button.setStyle(buttonStyleOff);

        setStyle("-fx-cursor: hand");
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;

        if (state) {
            button.setStyle(buttonStyleOn);
            back.setFill(Color.valueOf("#80C49E"));
            setAlignment(button, Pos.CENTER_RIGHT);
        } else {
            button.setStyle(buttonStyleOff);
            back.setFill(Color.valueOf("#ced5da"));
            setAlignment(button, Pos.CENTER_LEFT);
        }

        if (onChange != null) onChange.handle(null);
    }

    public SwitchButton() {
        init();
        click = new EventHandler<Event>() {
            @Override
            public void handle(Event e) {
                setState(!state);
            }
        };

        button.setFocusTraversable(false);
        setOnMouseClicked(click);
        button.setOnMouseClicked(click);
    }
}