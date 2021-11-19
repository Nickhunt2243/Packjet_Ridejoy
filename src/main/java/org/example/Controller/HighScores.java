package org.example.Controller;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.util.Duration;

public class HighScores {
    private final AnchorPane anchorPane;
    private final Death death;
    private final DatabaseConnect DB = new DatabaseConnect();
    private final String Playername;
    private int counter;
    private Text counterText;
    private Timeline highScoresTLine;

    public void HighScoresStart(){
        DrawCounterPane();
        Counter();
        int boostMultiplier = 1;
        highScoresTLine = new Timeline(new KeyFrame(Duration.seconds(boostMultiplier), event -> Counter()));
        highScoresTLine.setCycleCount(Animation.INDEFINITE);
        highScoresTLine.play();
    }

    private void Counter() {
        counter++;
        setCounterText(String.format("%d", counter));
        if (death.getIsDead()) {
            DB.AddScore(Playername, counter);
            User user = DB.getTopScores();
        }
    }

    private void DrawCounterPane(){
        AnchorPane counterLabel = new AnchorPane();
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        double screenWidth = screenBounds.getMaxX();
        counterLabel.setLayoutX(screenWidth -50);
        counterLabel.setLayoutY(50);
        counterLabel.setPrefSize(100, 50);

        this.counterText = new Text();
        this.counterText.setTextAlignment(TextAlignment.CENTER);
        this.counterText.setFont(Font.font("Comic Sans",20));
        counterText.setText(String.format("%d", this.counter));
        counterLabel.getChildren().add(counterText);
        anchorPane.getChildren().add(counterLabel);
    }

    public Timeline getHighScoresTLine() {
        return highScoresTLine;
    }

    public void setCounterText(String count){
       this.counterText.setText(count);
    }

    public HighScores(AnchorPane anchorPane, String Playername, Death death){
        this.anchorPane = anchorPane;
        this.Playername=Playername;
        this.death = death;
        counter = 0;
        HighScoresStart();
    }
}


