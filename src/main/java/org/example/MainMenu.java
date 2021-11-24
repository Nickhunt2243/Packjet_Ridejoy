package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.File;
import java.io.IOException;
import java.util.Objects;


public class MainMenu {

    //*** STATIC FIELD VARIABLES ***//
    /**
     * The String containing the players valid name that they entered.
     */
    private static String playerNameString;

    //*** NON-STATIC FIELD VARIABLES ***//
    /**
     * The label that holds the error for the name input.
     */
    private Label nameInputError;
    /**
     * The Owl logo you see in the middle of the screen.
     */
    @FXML
    ImageView logo;
    /**
     * The button that starts the game. The onAction event will send the user back to App to then send us to gameGUI
     * with all the necessary variables.
     */
    @FXML
    Button startButton;
    /**
     * The button that will send the user to the high scores menu where they can see who holds the top five scores.
     */
    @FXML
    Button highScoresButton;
    /**
     * The button that sends the user to the options menu.
     */
    @FXML
    Button optionsButton;
    /**
     * The TextField for the user to enter their name in. It requires a string one to four characters.
     */
    @FXML
    TextField name;
    /**
     * THis is the pane that every object will be added to within the main menu.
     */
    @FXML
    Pane Pane;
    /**
     * This method is the Initialize FXML method, it is called prior to the stage showing its content. It will
     * initialize the positions of all the object that display on the main menu.
     */
    @FXML
    private void initialize(){
        File file = new File("Image/OwlImage.jpg");
        Image image = new Image(file.toURI().toString());

        playerNameString = Objects.requireNonNullElse(App.getPlayerName(), "Ex: John (1-4 Char)");

        logo.setImage(image);

        name.setText(playerNameString);
        name.setFont(Font.font("Comic Sans MS"));

        startButton.setFont(Font.font("Comic Sans MS"));
        highScoresButton.setFont(Font.font("Comic Sans MS"));
        optionsButton.setFont(Font.font("Comic Sans MS"));

        nameInputError = new Label("*Character Limit Exceeded");
        nameInputError.setTextFill(Color.RED);
        nameInputError.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 12));
        nameInputError.setLayoutX(name.getLayoutX()-35);
        nameInputError.setLayoutY(name.getLayoutY()-30);
    }
    /**
     * This is the onAction event for the start game button. This uses App to send you to the game screen.
     *
     * @throws IOException: Possible IOException within setting the root. So App will handle the exception.
     */
    @FXML
    private void StartGame() throws IOException {
        if (nameIsValid()) {
            changePlayerName();
            App.setRoot("GameGUI", null, null, null, playerNameString);
        }
    }
    /**
     * This is the onAction event for the High Scores button. This uses App to send you to the high scores menu.
     *
     * @throws IOException: Possible IOException within setting the root. So App will handle the exception.
     */
    @FXML
    private void SwitchToHighScoresMenu() throws IOException {
        if (nameIsValid()){
            changePlayerName();
            App.setRoot("HighScores", null, null, null, playerNameString);
        }
    }
    /**
     * This is the onAction event for the Options menu button. This uses App to send you to the options menu.
     *
     * @throws IOException: Possible IOException within setting the root. So App will handle the exception.
     */
    @FXML
    private void SwitchToOptionsMenu() throws IOException {
        if (nameIsValid()){
            changePlayerName();
            App.setRoot("Options", null, null, null, playerNameString);
        }
    }
    /**
     * This method is used to remove the nameInputError from the Pane everytime you click into the name TextField.
     * This is done as when the user clicks in the name TextField, it is assumed they will fix what is throwing the
     * error. If they do not, then another error will be thrown.
     */
    @FXML
    private void RemoveLabel(){
        Pane.getChildren().remove(nameInputError);
        name.setText("");
    }
    /**
     * This method is used to check the validity of the name that the character entered. It is called everytime the
     * player tries to exit the scene (clicks the startGame, highScores, or options buttons).
     *
     * @return The boolean of whether the name is valid.
     */
    private boolean nameIsValid(){
        String tmpName = name.getText();
        if (tmpName.equals("Ex: John (1-4 Char)") || tmpName.length() == 0) {
            nameInputError.setText("*Input Your Name");
            if (!Pane.getChildren().contains(nameInputError))
                Pane.getChildren().add(nameInputError);
            return false;
        } else if (tmpName.length() >=5) {
            nameInputError.setText("*Character Limit Exceeded (1-4)");
            if (!Pane.getChildren().contains(nameInputError))
                Pane.getChildren().add(nameInputError);
            return false;
        }
        return true;
    }
    /**
     * The method that is used to update the players name if it is valid.
     */
    private void changePlayerName(){
        playerNameString = name.getText();
    }


}