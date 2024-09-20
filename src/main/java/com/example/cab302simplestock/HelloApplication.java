package com.example.cab302simplestock;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/*
unit testing: git commits run through unit testing


build & test script


Home Page:
    - List all the user's inventories
        ^ create mock list of inventories for testing
    - Create functionality for adding a category
    - Improve UI, add images, colour, show the username instead of {USER}

View Category:
    - List all items
        ^ use a list of mock items
    - Implement search to filter items


 */


public class HelloApplication extends Application {
    // Constants defining the window title and size
    public static final String TITLE = "Simple Stock";
    public static final int WIDTH = 640;
    public static final int HEIGHT = 360;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("loginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
        stage.setTitle(TITLE);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}