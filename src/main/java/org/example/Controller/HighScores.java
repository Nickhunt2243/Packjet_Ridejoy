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

    /**
     * The instantiation of the database that is used to save high scores when the player dies.
     */
    private final DatabaseConnect DB = new DatabaseConnect();
    /**
     * The counter that is used for the score tracker.
     */
    private int counter;
    /**
     * The Text object that is added to the counterLabel Pane to display the score as the player progresses
     * through the game.
     */
    private Text counterText;
    /**
     * The TimeLine that will increment the score, counter, and set the Text, counterText, object.
     */
    private final Timeline highScoresTLine;
    /**
     * The method that is called in the highScoresTLine to update the counter every 1 second. This continuously
     * increments the count (highScore) until the player dies. When the player dies, the counter is sent to the data
     * base to be saved, and the highScoresTLine is stopped in gameGUI.
     *
     * @param playerName: The name of the player to be saved in the database.
     * @param death:      The instantiation of the death class from gameGUI.
     */
    private void Counter(String playerName, Death death) {
        counter++;
        setCounterText(String.format("%d", counter));
        if (death.getIsDead()) {
            DB.AddScore(playerName, counter);
            //User user = DB.getTopScores();
        }
    }
    /**
     * A method that instantiates and initializes a pane that holds the counter for high scores.
     *
     * @param anchorPane: The anchorPane of the game from gameGUI.
     */
    private void DrawCounterPane(AnchorPane anchorPane){
        AnchorPane counterLabel = new AnchorPane();
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        double screenWidth = screenBounds.getMaxX();

        counterLabel.setLayoutX(screenWidth -50);
        counterLabel.setLayoutY(50);
        counterLabel.setPrefSize(100, 50);

        counterText = new Text();
        counterText.setTextAlignment(TextAlignment.CENTER);
        counterText.setFont(Font.font("Comic Sans",20));
        counterText.setText(String.format("%d", counter));

        counterLabel.getChildren().add(counterText);
        anchorPane.getChildren().add(counterLabel);
    }
    /**
     * The getter of the highScoresTLine.
     *
     * @return The highScoresTLine.
     */
    public Timeline getHighScoresTLine() {
        return highScoresTLine;
    }
    /**
     * This takes in the current score of the player and will change the text of the counterText Text object.
     *
     * @param count:
     */
    public void setCounterText(String count){
       this.counterText.setText(count);
    }

    /**
     * The constructor for the HighScores class. Takes in the AnchorPane of the game, playerName, and death
     * from gameGUI. It instantiated in gameGUI to start the score tracker. It will add a Text object to the screen so
     * the player will be able to see the current score as they play.
     *
     * @param anchorPane: The game Pane from gameGUI
     * @param playerName: The name of the player that will be sent to the data base for storage with their score.
     * @param death:      This is the death method. Used to commit the players name and score to the data base when the
     *                    player dies.
     */
    public HighScores(AnchorPane anchorPane, String playerName, Death death){
        counter = 0;
        DrawCounterPane(anchorPane);
        Counter(playerName, death);
        int boostMultiplier = 1;
        highScoresTLine = new Timeline(new KeyFrame(Duration.seconds(boostMultiplier), event -> Counter(playerName, death)));
        highScoresTLine.setCycleCount(Animation.INDEFINITE);
        highScoresTLine.play();
    }
}


