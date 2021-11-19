package org.example.Model;


import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;
import javafx.util.Duration;

import java.io.File;

public class Player implements EventHandler<KeyEvent> {
    /**
     * The String containing the player's name.
     */
    private final String playerName;
    /**
     * The image of the player.
     */
    private final Image fBrown;
    /**
     * The image of the player flapping its wings to create an animation.
     */
    private final Image fBrown2;
    /**
     * The AnimationTimer that will handle the "gravity" of the game.
     */
    private AnimationTimer timer;
    /**
     * The ImageView of the player.
     */
    private ImageView showPlayer;
    /**
     * The boolean that tells if the space bar is being pressed and the player should go up.
     */
    private boolean goUp;
    /**
     * The boolean that tells if
     */
    private boolean toggle;


    /**
     * The Constructor Player holds an easter egg for our professor, Ruth Kurniawati. If the player
     * happened to enter their name as "ruth" then the player's character would be changed
     * to her dog, Bertie. Otherwise it was set to an owl, as the Universities mascot was an owl.
     *
     * @param name: A string of the players name.
     */
    public Player(String name){


        this.playerName = name;
        if (this.playerName.equalsIgnoreCase("ruth")) {
            fBrown = new Image(new File("Image/Dodge1.png").toURI().toString());
            fBrown2 = new Image(new File("Image/Dodge2.png").toURI().toString());
        } else {
            fBrown = new Image(new File("Image/OwlBrown.png").toURI().toString());
            fBrown2 = new Image(new File("Image/OwlBrown2.png").toURI().toString());
        }
    }

    public void initialize(double screenHeight) {
        // creates an imageview players

        showPlayer = new ImageView(fBrown);

        //sets Players position
        showPlayer.setX(200);
        showPlayer.setY(200);
        showPlayer.setFitHeight(77.31);
        showPlayer.setFitWidth(75.6);



        //if (!isDead) {
        Timeline tline = new Timeline(new KeyFrame(Duration.millis(250), event -> animate())); //change to 40
        tline.setCycleCount(Timeline.INDEFINITE);
            tline.play();


            //Timer for movement of sprite
            timer = new AnimationTimer() {
                final double delta = 6;


                @Override
                public void handle(long l) {
                    if (goUp && showPlayer.getY() > 0) showPlayer.setY(showPlayer.getY() - delta);
                    if (!goUp && showPlayer.getY() < screenHeight - 190) showPlayer.setY(showPlayer.getY() + delta);
                }
            };
            timer.start();
    }

    @Override
    public void handle(KeyEvent keyEventPlayer) {
        //Handle for key pressed
        if (keyEventPlayer.getEventType().equals(KeyEvent.KEY_PRESSED)) {
            if (keyEventPlayer.getCode() == KeyCode.SPACE) {
                goUp = true;

            }//Handle for key released
        }else if (keyEventPlayer.getEventType().equals(KeyEvent.KEY_RELEASED)){
            if (keyEventPlayer.getCode() == KeyCode.SPACE) {
                goUp = false;
            }
        }
    }

    public void animate(){
        String flap = "flap";
        if (goUp){
            if (toggle){
                showPlayer.setImage(fBrown2);
                toggle=false;
            }else {
                showPlayer.setImage(fBrown);
                toggle=true;
            }
            playSoundFX(flap);
        }
    }

    public ImageView getPlayerImageView(){
        return showPlayer;
    }

    public static void playSoundFX(String event) {
        if (event.equals("flap")) {
            Media media = new Media(new File("Songs/WingJump.mp3").toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setVolume(.1);
            mediaPlayer.play();
        }
    }

    public AnimationTimer getTimer(){
        return timer;
    }

    public void setStage(){
    }

    public String getPlayerName() {
        return playerName;
    }
}