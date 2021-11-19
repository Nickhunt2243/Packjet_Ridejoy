package org.example.Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.awt.*;
import java.util.HashMap;

public abstract class Enemy {

    /**
     * The height of the players screen. Used for keeping images in bounds.
     */
    protected static final double SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    /**
     * The width of the players screen. Used for keeping images in bounds.
     */
    protected static final double SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    /**
     * The HashMap of images. This is mainly used for the animation of the enemies.
     */
    private final HashMap<Integer, Image> imageHash = new HashMap<>();
    /**
     * The type of enemy that was instantiated.
     */
    private final enemyType enemyTypeEnum;
    /**
     * The ImageView of the enemy.
     */
    private ImageView enemyImageView;
    /**
     * The type of enemy stored as enums.
     */
    public enum enemyType {
        SPIKE,
        ZAPPER,
        QUIZMISSILE
    }
    /**
     * Returns the HashMap of the enemy images.
     *
     * @return The HashMap of the enemy images.
     */
    public HashMap<Integer, Image> getImageHash(){
        return imageHash;
    }
    /**
     * This method initialized and sets the HashMap that holds the enemy images.
     *
     * @param image1: The first image of the enemy.
     * @param image2: The second image of the enemy.
     * @param image3: The third image of the enemy.
     */
    public void setImageHash(Image image1, Image image2, Image image3) {
        imageHash.put(1, image1);
        imageHash.put(2, image2);
        imageHash.put(3, image3);
    }
    /**
     * This will set the correct image of the enemy based on the previous image. It will use the imageHash
     * to determine what the next image should be. The newToggle will be the key of the new image that needs
     * to be added.
     *
     * @param newToggle: The key of the new image to be added.
     */
    public void setEnemyImage(int newToggle){
        this.enemyImageView.setImage(getImageHash().get(newToggle));
    }
    /**
     * Returns the current Image of the enemy's ImageView.
     *
     * @return The current Image of the enemy's ImageView.
     */
    public Image getEnemyImage(){
        return getImageView().getImage();
    }
    /**
     * Returns the type of enemy instantiated.
     *
     * @return The type of enemy instantiated.
     */
    public enemyType getEnemyType(){
        return enemyTypeEnum;
    }
    /**
     * Returns the ImageView of the enemy.
     *
     * @return The ImageView of the enemy.
     */
    public ImageView getImageView(){
        return enemyImageView;
    }
    /**
     * Sets the ImageView of the enemy.
     *
     * @param enemyImageView: The ImageView to be set.
     */
    public void setImageView(ImageView enemyImageView){
        this.enemyImageView = enemyImageView;
    }
    /**
     * The constructor of Enemy is here to initialize the enemyType.
     *
     * @param enemyTypeEnum: The type of enemy being instantiated.
     */
    protected Enemy(enemyType enemyTypeEnum) {
        this.enemyTypeEnum = enemyTypeEnum;
    }

}





