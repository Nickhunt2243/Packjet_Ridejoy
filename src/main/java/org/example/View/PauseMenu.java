package org.example.View;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import org.example.App;
import org.example.Controller.DatabaseConnect;
import org.example.Controller.Music;
import org.example.Controller.User;

import java.io.IOException;

public class PauseMenu {
    private double PauseMenuWidth;
    private double PauseMenuHeight;
    private AnchorPane pauseMenu;
    private Button mainMenu;
    private Label highScoresTitle;
    private MediaPlayer mPlayer;
    private double musicVolume;

    PauseMenu(Music music) {
        InitializePauseMenu();
        InitializeHighScores();
        InitializeButton();
        InitializeVolumeSlider(music);
        pauseMenu.getChildren().add(mainMenu);
    }

    public AnchorPane SetPausePane(){
        return pauseMenu;
    }

    public void InitializeHighScores(){
        highScoresTitle = new Label("High Scores");
        highScoresTitle.setPrefSize(pauseMenu.getPrefWidth()/2, pauseMenu.getPrefHeight()-600);
        highScoresTitle.setLayoutX(pauseMenu.getPrefWidth()/2-highScoresTitle.getPrefWidth()/2);
        highScoresTitle.setLayoutY(0);
        highScoresTitle.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 70));
        highScoresTitle.setAlignment(Pos.CENTER);
        highScoresTitle.setTextFill(Color.WHITE);
        pauseMenu.getChildren().add(highScoresTitle);


        DatabaseConnect DB = new DatabaseConnect();
        User HighestScores = DB.getTopScores();

        for (int i=0;i<HighestScores.getSize();i++){
            String name = HighestScores.getNames(i);
            int score = HighestScores.getScores(i);
            CreateHighScoreLabel(name, score, i);
        }
    }

    public void CreateHighScoreLabel(String name, int Score, int positionScale){
        Label highScoreLabel = new Label(name + ":     "  + Score);
        highScoreLabel.setPrefSize(300, 100);
        highScoreLabel.setLayoutX(PauseMenuWidth/2-highScoreLabel.getPrefWidth()/2);
        highScoreLabel.setLayoutY(positionScale*(highScoreLabel.getPrefHeight())+highScoresTitle.getPrefHeight());
        highScoreLabel.setFont(Font.font("Comic Sans MS", 45));
        highScoreLabel.setAlignment(Pos.CENTER);
        highScoreLabel.setTextFill(Color.WHITE);
        pauseMenu.getChildren().add(highScoreLabel);
        highScoreLabel.setId("highScoresLabel");
    }

    private void InitializePauseMenu(){
        pauseMenu = new AnchorPane();
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        double screenWidth = screenBounds.getMaxX();
        double screenHeight = screenBounds.getMaxY();
        pauseMenu.setPrefSize(screenWidth-720,screenHeight-380);
        PauseMenuWidth = pauseMenu.getPrefWidth();
        PauseMenuHeight = screenBounds.getMaxY();
        pauseMenu.setLayoutX((screenWidth-pauseMenu.getPrefWidth())/2);
        pauseMenu.setLayoutY((screenHeight-pauseMenu.getPrefHeight())/2);
        this.mPlayer = mPlayer;

        AnchorPane colorPane = new AnchorPane();
        colorPane.setPrefSize(pauseMenu.getPrefWidth(), pauseMenu.getPrefHeight());
        colorPane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        colorPane.setOpacity(.9);
        pauseMenu.getChildren().add(colorPane);
    }

    private void InitializeButton(){
        mainMenu = new Button("Main Menu");
        mainMenu.setPrefSize(112,35);
        mainMenu.setOnAction(e -> {
            try {
                App.setRoot("/Model/primary",null, null, null, null);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        mainMenu.setFont(Font.font("Comic Sans MS", 17));

        mainMenu.setLayoutX(pauseMenu.getPrefWidth()/2 - mainMenu.getPrefWidth()/2);
        mainMenu.setLayoutY(pauseMenu.getPrefHeight() - 50);
    }

    private void InitializeVolumeSlider(Music music){
        Slider volume = new Slider(0,100, music.getVolume());
        volume.setPrefSize(150,30);
        volume.setShowTickMarks(true);
        volume.setShowTickLabels(true);
        volume.setScaleX(1.2);
        volume.setLayoutY(1.2);
        volume.setMajorTickUnit(50);
        volume.setBlockIncrement(5f);
        volume.setLayoutX(pauseMenu.getPrefWidth() / 2-volume.getPrefWidth() / 2);
        volume.setLayoutY(pauseMenu.getPrefHeight()-100);
        volume.setOrientation(Orientation.HORIZONTAL);
        volume.valueProperty().addListener((observable, oldValue, newValue) -> music.setVolume(newValue.doubleValue()));

        Label volumeLabel = new Label("Volume: ");
        volumeLabel.setPrefSize(75,20);
        volumeLabel.setLayoutX(volume.getLayoutX()-volumeLabel.getPrefWidth()-10);
        volumeLabel.setLayoutY(pauseMenu.getPrefHeight()-100);
        volumeLabel.setFont(Font.font("Comic Sans MS", 17));
        volumeLabel.setTextFill(Color.WHITE);

        pauseMenu.getChildren().addAll(volume, volumeLabel);
    }
}

