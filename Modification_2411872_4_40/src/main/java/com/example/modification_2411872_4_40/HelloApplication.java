package com.example.modification_2411872_4_40;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("createNewSuplierView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Project Modification!");
        stage.setScene(scene);
        stage.show();
    }
}
