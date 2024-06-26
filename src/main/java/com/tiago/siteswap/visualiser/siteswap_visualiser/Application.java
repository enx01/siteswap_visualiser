package com.tiago.siteswap.visualiser.siteswap_visualiser;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("welcome-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 400);
        stage.setResizable(false);

        stage.setTitle("Siteswap visualiser");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}