package org.example.Model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import org.example.Controller.Collision;

import java.io.File;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Powerups {
    public boolean Boost;
    public boolean Shield;
    public boolean isPowerup;
    public int PowerupType;
    private final double screenX;
    private final double screenY;
    private AnchorPane movingPane;
    public Collision collision;
    public Timeline PowerupTimeline;
    public Timeline tlinePowerupCreation;
    public Timeline tlineAnimate;
    int randomYPos;
    int randomPtype;
    private double move;
    private boolean isPaused;
    private boolean toggle;


    Image BoostIMG;
    Image ShieldIMG;
    public ImageView ShowBoost;
    public ImageView ShowShield;

    File BoostFile = new File("Image/Boost1.png");
    File BoostFile2 = new File("Image/Boost2.png");
    File ShieldFile = new File("Image/Shield1.png");
    File ShieldFile2 = new File("Image/Shield2.png");


    public Powerups(AnchorPane anchorPane, Collision collision, double move, double screenX, double screenY){
        this.screenX=screenX;
        this.screenY=screenY;
        this.movingPane=anchorPane;
        this.collision=collision;
        this.move=move;

        this.Boost = false;
        this.Shield = false;
        this.isPowerup = false;

        this.tlinePowerupCreation = new Timeline(new KeyFrame(Duration.millis(8000), event -> createPup()));
        tlinePowerupCreation.setCycleCount(Timeline.INDEFINITE);
        tlinePowerupCreation.play();

        PowerupTimeline = new Timeline(new KeyFrame(Duration.millis(4), event -> PowerupMovement()));
        PowerupTimeline.setCycleCount(Timeline.INDEFINITE);
        PowerupTimeline.play();

        tlineAnimate = new Timeline(new KeyFrame(Duration.millis(250), event -> animate()));
        tlineAnimate.setCycleCount(Timeline.INDEFINITE);
        tlineAnimate.play();

    }

    // creates a boost power up that gives temporary invincibility and speeds up the game
    public void createPup(){
        Random rand = new Random();
        Random R = new Random();
        this.randomPtype = R.nextInt(2);
        this.PowerupType=randomPtype;
        if (PowerupType==0) {
            if (ShowBoost != null) {
                if (ShowBoost.getX() < 0) {
                    movingPane.getChildren().remove(ShowBoost);
                }
            }
            BoostIMG = new Image(BoostFile.toURI().toString());
            ShowBoost = new ImageView(BoostIMG);
            ShowBoost.setX(screenX -50);
            this.randomYPos = rand.nextInt((int) screenY - 200);
            ShowBoost.setY(randomYPos);
            ShowBoost.setFitHeight(100);
            ShowBoost.setFitWidth(126);
            movingPane.getChildren().add(ShowBoost);
        }else if (PowerupType==1){
            if (ShowShield != null) {
                if (ShowShield.getX() < 0) {
                    movingPane.getChildren().remove(ShowShield);
                }
            }
            ShieldIMG = new Image(ShieldFile.toURI().toString());
            ShowShield = new ImageView(ShieldIMG);
            ShowShield.setX(screenX -50);
            this.randomYPos = rand.nextInt((int) screenY -200);
            ShowShield.setY(randomYPos);
            ShowShield.setFitHeight(100);
            ShowShield.setFitWidth(126);
            movingPane.getChildren().add(ShowShield);
        }

        //Play sound effect for boost
        String event = "shield";
        playSoundFX(event);
    }

    public Timeline getTlinePowerupCreation() {
        return tlinePowerupCreation;
    }

    public Timeline getTlineAnimate() {
        return tlineAnimate;
    }

    public Timeline getPowerupTimeline() {
        return PowerupTimeline;
    }

    public void PowerupMovement(){
        if(PowerupType==0 && ShowBoost != null){
            ShowBoost.setX(ShowBoost.getX()-move);
        }else if (PowerupType==1 && ShowShield != null){
            ShowShield.setX(ShowShield.getX()-move);
        }
        if (isPaused){
            tlinePowerupCreation.pause();
            PowerupTimeline.pause();
        }
    }
    public void animate(){
        if (PowerupType==0 && ShowBoost !=null) {
            if (toggle == true) {
                BoostIMG = new Image(BoostFile.toURI().toString());
                ShowBoost.setImage(BoostIMG);
                toggle = false;
            } else if (toggle == false) {
                BoostIMG = new Image(BoostFile2.toURI().toString());
                ShowBoost.setImage(BoostIMG);
                toggle = true;
            }
        }else if (PowerupType==1 && ShowShield != null){
            if (toggle == true) {
                ShieldIMG = new Image(ShieldFile.toURI().toString());
                ShowShield.setImage(ShieldIMG);
                toggle = false;
            } else if (toggle == false) {
                ShieldIMG = new Image(ShieldFile2.toURI().toString());
                ShowShield.setImage(ShieldIMG);
                toggle = true;
            }
        }
    }
    public void PowerupTimer(){
        Boost = true;
        isPowerup = true;
        Timer tm = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Boost = false;
                isPowerup = false;
            }
        };
        tm.schedule(task, 5000);
    }
    public void PowerupTimerShield(){
        Shield=true;
        isPowerup = true;
        Timer tm = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Shield= false;
                isPowerup = false;
            }
        };
        tm.schedule(task, 3000);
    }
    public void SetMove(double move) {
        this.move = move;
    }

    public static void playSoundFX(String event) {
        String action = event;

        if (action.equals("boost")) {
            Media media = new Media(new File("Songs/LessGOOOOO.mp3").toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            //mediaPlayer.play();
        } else if (action.equals("shield")) {
            Media media = new Media(new File("Songs/I_Pull_Up.mp3").toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
           // mediaPlayer.play();
        }
    }
    public void setIsPaused(boolean isPaused) {
        this.isPaused = isPaused;
    }

}
