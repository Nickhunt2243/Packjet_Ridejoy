package org.example.Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.File;

public class ZapperEnemy extends Enemy{


    /**
     * The constructor ZapperEnemy will take in a random Y position for the zapper enemy type and creates the image
     * view with the appropriate x,y position, image, and fit. It will also create a HashMap of the corresponding
     * images to make toggling through the animations easier.
     *
     * @param randomYPos: The random Y position for this enemy.
     */
    public ZapperEnemy(int randomYPos) {
        super(enemyType.ZAPPER);
        Image zapperImage3 = new Image(new File("Image/zapper3.png").toURI().toString());
        Image zapperImage2 = new Image(new File("Image/zapper2.png").toURI().toString());
        Image zapperImage1 = new Image(new File("Image/zapper1.png").toURI().toString());

        super.setImageHash(zapperImage1, zapperImage2, zapperImage3);
        ImageView showZapper = new ImageView(zapperImage1);
        showZapper.setX(SCREEN_WIDTH - 50);
        showZapper.setY(randomYPos);
        showZapper.setFitWidth(182);
        showZapper.setFitHeight(277);
        setImageView(showZapper);
    }
}
