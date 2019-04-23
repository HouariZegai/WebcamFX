
package com.houarizegai.java;

import com.houarizegai.java.controllers.CaptureCameraController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/houarizegai/resources/views/CaptureCamera.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

  
    public static void main(String[] args) {
        Application.launch(args);
    }
    
    @Override
    public void stop() {
        CaptureCameraController.isCapture = true;
    }
    
}
