package main;

import javafx.application.Application;
import javafx.stage.Stage;

public class Calendar extends Application {

    public Stage window;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        window = stage;

    }
}
