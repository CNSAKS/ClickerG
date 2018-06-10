/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickerg.classes.others.gameLoop;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;

/**
 *
 * @author cnsak
 */
public class GameLoop{
    private int FPS = 100;
    private String id;
    private int actual;
    private int frames;
    private ImageView img;
    private String type;
    private boolean close = false;
    ScheduledExecutorService executor;
    
    public GameLoop(String id, ImageView img, String type) {
        this.frames = new File("src/clickerg/animations/"+type+"/id"+id).listFiles().length;
        this.id = id;
        this.img = img;
        this.actual = 1;
        this.type = type;
    }
  
    public void startGame() {
        executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                updateGame();
                if(close){executor.shutdown();}
                
            }
        }, 100, FPS, TimeUnit.MILLISECONDS);
        
    }

    private void updateGame() {
        BufferedImage bufferedImage;
        File file;
        if(actual <= frames){
            file = new File("src/clickerg/animations/"+type+"/id"+id+"/id"+id+" ("+actual+").gif");
            actual++;
        }else{
            file = new File("src/clickerg/animations/"+type+"/id"+id+"/id"+id+" ("+1+").gif");
            actual = 1;
            actual++;
        }
        
        try {
        bufferedImage = ImageIO.read(file);
        img.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
        switch(type){
            case "background":  img.setFitHeight(400);
                                img.setFitWidth(600);
                                break;
            case "boss":        img.setFitHeight(200);
                                img.setFitWidth(200);
                                break;
            case "heroe":       img.setFitHeight(200);
                                img.setFitWidth(200);
                                break;
            default:            img.setFitHeight(200);
                                img.setFitWidth(200);
                                break;
        }
        img.setPreserveRatio(false);
        } catch (IOException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
}
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        setFrames(new File("src/clickerg/animations/"+type+"/id"+id).listFiles().length);
    }

    public void setActual(int actual) {
        this.actual = actual;
    }

    public void setFrames(int frames) {
        this.frames = frames;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setClose(boolean close) {
        this.close = close;
    }
    
}