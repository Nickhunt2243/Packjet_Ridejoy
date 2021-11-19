package org.example.Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.File;

public class SpikeEnemy extends Enemy {


    /**
     * The constructor SpikeEnemy will create the spike enemy type's image view with the appropriate x,y position,
     * image, and fit. It will also create a HashMap of the corresponding images to make toggling through
     * the animations easier.
     */
    public SpikeEnemy() {
        super(Enemy.enemyType.SPIKE);
        Image spikeImage3 = new Image(new File("Image/Spike3.png").toURI().toString());
        Image spikeImage2 = new Image(new File("Image/Spike2.png").toURI().toString());
        Image spikeImage1 = new Image(new File("Image/Spike1.png").toURI().toString());

        setImageHash(spikeImage1, spikeImage2, spikeImage3);

        ImageView showSpike = new ImageView(spikeImage1);
        showSpike.setY(SCREEN_HEIGHT - 220);
        showSpike.setX(SCREEN_WIDTH - 50);
        showSpike.setFitHeight(100);
        showSpike.setFitWidth(126);
        setImageView(showSpike);
    }
}
