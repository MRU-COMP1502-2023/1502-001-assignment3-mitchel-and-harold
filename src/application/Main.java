package application;
import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.logging.FileHandler;



import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class Main extends Application {

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

    }

    public static void main(String[] args) throws SecurityException, IOException{
    	 final Logger logger = Logger.getLogger(Main.class.getName());
    	    FileHandler fileHandler = new FileHandler("myLog.txt", true); // true to append
    	    logger.addHandler(fileHandler);
    	    SimpleFormatter formatter = new SimpleFormatter();
    	    fileHandler.setFormatter(formatter);
    	    logger.info("Logging started");

    	   
    	launch(args);
       
    }
}
