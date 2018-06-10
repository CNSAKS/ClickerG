/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickerg.classes.others.volatiles;

import clickerg.classes.others.gameLoop.GameLoop;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Dragon
 */
public class AnimationVolatile {
    private int actual;
    private int msDuration;
    private ImageView iv;

    private boolean close = false;
    ScheduledExecutorService executor;
    GameLoop gamePickaxe;
    
    public AnimationVolatile(int msDuration, ImageView iv) {
        this.actual = 0;
        this.msDuration = msDuration;
        this.iv = iv;

        iv.setImage(new Image("/clickerg/visualsAndFiles/animations/extra/id0/id0 (1).gif"));
    }
    
    public void startTime() {
        this.actual = 0;
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
    public void play(){
        gamePickaxe = new GameLoop("0", iv, "extra");
        gamePickaxe.startGame();
    }
    private void updateTime() {
            actual+=100;
            if(actual>=msDuration){
               
                closeThread();
            }
    }
    
    public void closeThread(){
       gamePickaxe.setClose(true);
       close = true; 
       //iv.setImage(new Image("/clickerg/animations/boss/id3/id3 (1).gif"));
       
    }
}
