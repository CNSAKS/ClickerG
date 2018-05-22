/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickerg;

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
    private static final int FPS = 100;
    private int id;
    private int actual;
    private int frames;
    private ImageView img;

    
    public GameLoop(int frames, int id, ImageView img) {
        this.frames = frames;
        this.id = id;
        this.img = img;
        this.actual = 1;
    }
  
    public void startGame() {
        ScheduledExecutorService executor = Executors
                .newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                updateGame();
            }
        }, 0, 1000 / frames, TimeUnit.MILLISECONDS);
    }

    private void updateGame() {
        System.out.println("game updated");
        BufferedImage bufferedImage;
        File file;
        if(actual <= frames){
             file = new File("src/clickerg/animations/id"+id+"/id"+id+"-"+actual+".gif");
            actual++;
        }else{
            file = new File("src/clickerg/animations/id"+id+"/id"+id+"-"+1+".gif");
            actual = 1;
            actual++;
        }
        
        try {
        bufferedImage = ImageIO.read(file);
        img.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
        } catch (IOException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
}
    }
}