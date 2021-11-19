package org.example.View;

import javafx.css.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.example.App;
import org.example.Controller.DatabaseConnect;
import org.example.Controller.User;

import java.io.IOException;

public class HighScoresMenu {

    private final Stage stage;
    private final String backgroundImageString;
    private AnchorPane highScoresPane;
    private double screenHeight;
    private double screenWidth;
    private Label highScoresTitle;

    public HighScoresMenu(Stage stage, String backgroundImageString){
        this.backgroundImageString = backgroundImageString;
        this.stage = stage;
    }

    public void HighScoresStart(){
        InitializePane();
        InitializeHighScores();
        InitializeMainMenuButton();
        Scene scene = new Scene(highScoresPane);
        stage.setScene(scene);
        stage.show();
    }


    public void InitializePane(){
        highScoresPane = new AnchorPane();
        highScoresPane.setPrefSize(1530,780);
        screenWidth = highScoresPane.getPrefWidth();
        screenHeight = highScoresPane.getPrefHeight();
        highScoresPane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        highScoresPane.setId("highScores");

    }

    public void InitializeHighScores(){
        highScoresTitle = new Label("High Scores");
        highScoresTitle.setPrefSize(highScoresPane.getPrefWidth()/2, highScoresPane.getPrefHeight()-600);
        highScoresTitle.setLayoutX(highScoresPane.getPrefWidth()/2-highScoresTitle.getPrefWidth()/2);
        highScoresTitle.setLayoutY(0);
        highScoresTitle.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 70));
        highScoresTitle.setAlignment(Pos.CENTER);
        highScoresTitle.setTextFill(Color.WHITE);
        highScoresPane.getChildren().add(highScoresTitle);


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
        highScoreLabel.setPrefSize(3*screenWidth/4, 100);
        highScoreLabel.setLayoutX(screenWidth/2-highScoreLabel.getPrefWidth()/2);
        highScoreLabel.setLayoutY(positionScale*(highScoreLabel.getPrefHeight())+highScoresTitle.getPrefHeight());
        highScoreLabel.setFont(Font.font("Comic Sans MS", 45));
        highScoreLabel.setAlignment(Pos.CENTER);
        highScoresPane.getChildren().add(highScoreLabel);
        highScoreLabel.setId("highScoresLabel");
    }

    public void InitializeMainMenuButton(){
        Button mainMenu = new Button("Main Menu");
        mainMenu.setPrefSize(112,30);
        mainMenu.setOnAction(e -> {
            try {
                App.setRoot("/Model/primary", null, null, backgroundImageString, null);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        mainMenu.setFont(Font.font("Comic Sans MS", 17));

        mainMenu.setLayoutX(screenWidth-mainMenu.getPrefWidth()-50);
        mainMenu.setLayoutY(screenHeight-50);
        highScoresPane.getChildren().add(mainMenu);

    }
}
