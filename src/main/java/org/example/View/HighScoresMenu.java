package org.example.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import org.example.App;
import org.example.Controller.DatabaseConnect;
import org.example.Controller.User;
import java.io.IOException;

public class HighScoresMenu {
    /**
     * The stage of the game from the App class.
     */
    private final Stage stage;
    /**
     * The pane that all the high scores will get added to.
     */
    private AnchorPane highScoresPane;
    /**
     * The height of the user's screen.
     */
    private double screenHeight;
    /**
     * The width of the user's screen.
     */
    private double screenWidth;
    /**
     * The Label that contains the string "High Scores" that is positioned at the top of the high scores pane.
     */
    private Label highScoresTitle;
    /**
     * This method is called as a helper method to clean up some of the code. It is used to instantiate and initialize
     * the objects to be added to the high scores pane. It will call methods that will receive the scores from
     * the database, initialize the high scores pane, and initialize the main menu button. Then it will
     * set the scene and show the stage.
     */
    public void HighScoresStart(){
        InitializePane();
        InitializeHighScores();
        InitializeMainMenuButton();
        Scene scene = new Scene(highScoresPane);
        stage.setScene(scene);
        stage.show();
    }
    /**
     * This helper method is used to initialize the high scores pane. It will set its size and background.
     */
    public void InitializePane(){
        highScoresPane = new AnchorPane();
        highScoresPane.setPrefSize(1530,780);
        screenWidth = highScoresPane.getPrefWidth();
        screenHeight = highScoresPane.getPrefHeight();
        highScoresPane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
    }
    /**
     * This helper method is used to add the title and connect to the database and retrieve the high scores.
     * Once it receives them it will add them to the high scores pane. It uses another helper method to add
     * the high score labels to the pane. As we know there are five it would be tedious to do this without a
     * loop. So, we decided it would be best to use a for loop.
     *
     * If connection is not made then an error message will be displayed.
     */
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
        if (HighestScores != null){
            for (int i=0;i<5;i++){
                String name = HighestScores.getNames(i);
                int score = HighestScores.getScores(i);
                CreateHighScoreLabel(name, score, i);
            }
        } else {
            Label errorLabel1 = new Label();
            Label errorLabel2 = new Label();

            errorLabel1.setText("Error: Cannot connect to database");
            errorLabel2.setText("High scores unobtainable");

            errorLabel1.setPrefSize(3*screenWidth/4, 80);
            errorLabel1.setLayoutX(screenWidth/2-errorLabel1.getPrefWidth()/2);
            errorLabel1.setLayoutY((errorLabel1.getPrefHeight())+highScoresTitle.getPrefHeight());
            errorLabel1.setFont(Font.font("Comic Sans MS", 45));
            errorLabel1.setAlignment(Pos.CENTER);

            errorLabel2.setPrefSize(3*screenWidth/4, 80);
            errorLabel2.setLayoutX(screenWidth/2-errorLabel2.getPrefWidth()/2);
            errorLabel2.setLayoutY(2*(errorLabel2.getPrefHeight())+highScoresTitle.getPrefHeight());
            errorLabel2.setFont(Font.font("Comic Sans MS", 45));
            errorLabel2.setAlignment(Pos.CENTER);

            highScoresPane.getChildren().addAll(errorLabel1, errorLabel2);
            errorLabel1.setId("errorLabel");
        }
    }
    /**
     * This is the helper method for the InitializeHighScores() method. We use this to add the high score labels to
     * the screen. It uses the index of the for loop from the InitializeHighScores() method as a scalar for the
     * Y position of each label. We do this so they are all evenly spaced. Otherwise they would be overlapping.
     *
     * @param name: The name of the player with the corresponding score.
     * @param Score: The score of the player with the corresponding score.
     * @param positionScale: The index from the for loop that is used as a scalar for the Y position.
     */
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
    /**
     * The helper method that is used to create the mainMenu button which will be used to send the user back to
     * the main menu. Once this onAction event has been set the button is added to the pane.
     */
    public void InitializeMainMenuButton(){
        Button mainMenu = new Button("Main Menu");
        mainMenu.setPrefSize(112,30);
        mainMenu.setOnAction(e -> {
            try {
                App.setRoot("/Model/primary", null, null, null, null);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        mainMenu.setFont(Font.font("Comic Sans MS", 17));
        mainMenu.setLayoutX(screenWidth-mainMenu.getPrefWidth()-50);
        mainMenu.setLayoutY(screenHeight-50);
        highScoresPane.getChildren().add(mainMenu);
    }
    /**
     * This method is the constructor of the HighScoresMenu class. Once it has initialized the stage it will call,
     * the HighScoresStart() method.
     *
     * @param stage: The stage from App.
     */
    public HighScoresMenu(Stage stage){
        this.stage = stage;
        HighScoresStart();
    }
}
