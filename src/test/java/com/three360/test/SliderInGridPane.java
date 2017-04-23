package com.three360.test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class SliderInGridPane extends Application {

    @Override
    public void start(Stage primaryStage) {
        GridPane root = new GridPane();
        root.add(new Label("Value:"), 0, 0);
        Slider slider = new Slider(0, 10, 5);
        GridPane.setHgrow(slider, Priority.ALWAYS);
        root.add(slider, 1, 0);
        primaryStage.setScene(new Scene(root, 500, 75));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}