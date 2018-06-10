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
import javafx.scene.layout.Pane;

/**
 *
 * @author cnsak
 */
public class DisablerVolatile{
    private int actual;
    private int msDuration;
    private Pane pane;
    private boolean close = false;
    ScheduledExecutorService executor;
    
    public DisablerVolatile(int msDuration, Pane pane) {
        this.actual = 0;
        this.msDuration = msDuration;
        this.pane = pane;
        disablePane();
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
                pane.setDisable(false);
                closeThread();
            }
    }
    
    public void closeThread(){
       close = true; 
    }
    
    private void disablePane(){
        pane.setDisable(true);
    }
}