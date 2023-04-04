package application;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.util.logging.Level;
import java.util.logging.Logger;


public class Main extends Application {

    private static final Logger LOGGER = Logger.getLogger("Mylogger");

    @Override
    public void start(Stage stage) throws IOException {
        // load the FXML file
        Parent parent = FXMLLoader.load(getClass().getResource("GUI.fxml"));

        // build the scene graph
        Scene scene = new Scene(parent);

        // display window
        stage.setTitle("Toy Program");
        stage.setScene(scene);
        stage.show();

        LOGGER.log(Level.INFO, "Application started successfully.");
    }

    public static void main(String[] args) {
    	
        LOGGER.log(Level.INFO, "Application is starting...");
        launch(args);
    }
}
