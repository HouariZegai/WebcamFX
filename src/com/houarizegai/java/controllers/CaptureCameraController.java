package com.houarizegai.java.controllers;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.github.sarxos.webcam.Webcam;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

public class CaptureCameraController implements Initializable {

    public static Webcam webcam;
    public static boolean isCapture = false; // This Variable for Stop Thread
    
    @FXML
    private ImageView imageView;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        webcam = Webcam.getDefault();
        webcam.setViewSize(new Dimension(640, 480));
        webcam.open();
        new VideoTacker().start();
        
    }
    
    @FXML
    private void btnCapture() {
        isCapture = true;
    }
    
    @FXML
    private void btnReload() {
        isCapture = false;
        new VideoTacker().start();
    }
    @FXML
    private void btnSave() {
        isCapture = true;
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images (*.png)", "*.png"));
        fileChooser.setTitle("Save Image");
        File file = fileChooser.showSaveDialog(imageView.getScene().getWindow());
        if (file != null) {
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(imageView.getImage(), null), "PNG", file);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    
    class VideoTacker extends Thread {
        @Override
        public void run() {
            while(!isCapture) {
                try {
                    imageView.setImage(SwingFXUtils.toFXImage(webcam.getImage(), null));
                    sleep(30);
                } catch (InterruptedException ex) {
                    //Logger.getLogger(CaptureCameraController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}


