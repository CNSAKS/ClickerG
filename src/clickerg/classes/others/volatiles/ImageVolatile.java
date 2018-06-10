/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickerg.classes.others.volatiles;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author cnsak
 */
public class ImageVolatile{
    private int actual;
    private int msDuration;
    private ImageView img;
    private String url;
    private boolean close = false;
    ScheduledExecutorService executor;
    
    public ImageVolatile(int msDuration, ImageView img,String url) {
        this.actual = 0;
        this.msDuration = msDuration;
        this.img = img;
        this.url = url;
        putImage();
    }
  
    public void startTime() {
        executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        updateTime();
                        if(close)executor.shutdown();
                    }
                });
                
                
            }
        }, 0, 100, TimeUnit.MILLISECONDS);
        
    }

    private void updateTime() {
            actual+=100;
            if(actual>=msDuration){
                img.setImage(null);
                closeThread();
            }
    }
    
    public void closeThread(){
       close = true; 
    }
    
    private void putImage(){
        try{
        img.setImage(new Image(this.url));
        img.setPreserveRatio(false);
        } catch (Exception e1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al cargar las imagenes");
            alert.setHeaderText("Ha habido un error al intentar cargar las imagenes");
            alert.setContentText("El juego se va a cerrar");

            alert.showAndWait();
            System.exit(0);
        }
    }
}