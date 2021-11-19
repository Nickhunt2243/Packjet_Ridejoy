package org.example.Controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.util.Duration;
import org.example.App;
import org.example.Model.Player;
import org.example.View.GameGUI;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Death {
    /**
     * The HashMap containing the death animation images.
     */
    private final HashMap<Integer, Image> imageHash = new HashMap<>();
    /**
     * The Instance of Player from GameGUI.
     */
    private final Player player;
    /**
     * The height of the players screen.
     */
    private final double screenHeight;
    /**
     * The instance of GameGUI created in App.
     */
    private GameGUI gameGUI;
    /**
     * The TimeLine that controls the animations for the death sequence.
     */
    private Timeline deathLine;
    /**
     * The toggle of the death animation images.
     */
    private int toggleDeath;
    /**
     * The boolean to tell if the player is dead.
     */
    private boolean isDead;
    /**
     * This method is the main part of Death. It is responsible for starting the animations, stopping all timers (using
     * the stopTimeLines() method from GameGUI), setting the toggle and boolean, setting the player image to the
     * first death animation image, and starting the animations.
     */
    public void deathSequence(){
        deathLine = new Timeline(new KeyFrame(Duration.millis(125), event -> DeathAnimation()));
        deathLine.setCycleCount(Timeline.INDEFINITE);
        gameGUI.stopTimeLines();
        toggleDeath = 1;
        isDead = true;
        player.getPlayerImageView().setImage(imageHash.get(1));
        deathLine.play();
    }
    /**
     * This method is responsible for any animations and movement the player will go through when he dies.
     * It will also drop the player 40px to make him appear as though he is falling. Once the player is below
     * the screen the deathLine TimeLine will stop and the scene will change.
     */
    public void DeathAnimation() {
        animatePlayer();
        player.getPlayerImageView().setY(player.getPlayerImageView().getY()+40);
        if (player.getPlayerImageView().getY() > screenHeight){
            deathLine.stop();
            switchToPrimary();
        }
    }
    /**
     * This method will first check what the ToggleDeath is set to. If it is 1, 2, or 3 then it will increment the
     * toggle. If it is 4 then it will set it back to 1, resetting the animation sequence. Once it has altered
     * toggleDeath, then it will set the player image to the image in the imageHash HashMap with the corresponding
     * key.
     */
    private void animatePlayer(){
        if (toggleDeath < 4){
            toggleDeath++;
        } else {
            toggleDeath = 1;
        }
        player.getPlayerImageView().setImage(imageHash.get(toggleDeath));
    }
    /**
     * This method is used as the reset button. By switching to the primary root we are able to start the game
     * over from the main screen.
     */
    @FXML
    private void switchToPrimary(){
        try {
            App.setRoot("/Model/primary", null, null, null, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Returns the isDead boolean.
     *
     * @return The isDead boolean.
     */
    public boolean getIsDead() {
        return isDead;
    }
    /**
     * This is used to instantiate the same instance of GameGUI as we did before so we may access the stopTimeLines()
     * method.
     *
     * @param gameGUI: The instance of gameGUI created in App.
     */
    public void setGameGUI(GameGUI gameGUI){
        this.gameGUI = gameGUI;
    }
    /**
     * This constructor will take in the instance of player from GameGUI and the height of the players screen. It
     * starts by assigning those parameters. It then works on initializing the correct images into the image hashmap.
     * This is necessary because of the "ruth" Easter Egg. If the players name is "ruth" it will assign different
     * images to the death animations.
     *
     * @param player: The instance of from GameGUI.
     * @param screenHeight: The height of the players screen.
     */
    public Death(Player player, double screenHeight){
        this.player = player;
        this.screenHeight = screenHeight;
        if (player.getPlayerName().equalsIgnoreCase("ruth")) {
            imageHash.put(1, new Image(new File("Image/Dodge1.png").toURI().toString()));
            imageHash.put(2, new Image(new File("Image/DodgeDeath1.png").toURI().toString()));
            imageHash.put(3, new Image(new File("Image/DodgeDeath2.png").toURI().toString()));
            imageHash.put(4, new Image(new File("Image/DodgeDeath3.png").toURI().toString()));
        } else {
            imageHash.put(1, new Image(new File("Image/OwlBrownDeath1.png").toURI().toString()));
            imageHash.put(2, new Image(new File("Image/OwlBrownDeath2.png").toURI().toString()));
            imageHash.put(3, new Image(new File("Image/OwlBrownDeath3.png").toURI().toString()));
            imageHash.put(4, new Image(new File("Image/OwlBrownDeath4.png").toURI().toString()));
        }
    }
}
