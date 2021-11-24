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


public class MainMenu {

    //Main menu logo
    @FXML
    ImageView logo;
    private static ColorPicker colorPicker;
    private static ChoiceBox<String> musicChoice;
    private static String backGroundImage;
    @FXML
    Button primaryButton;
    @FXML
    Button highScoresButton;
    @FXML
    Button optionsButton;
    @FXML
    TextField name;
    @FXML
    Pane Pane;
    private static String PlayerNameString;
    private Label nameInputError;

    @FXML
    private void initialize(){
        //Initializes the image into the main stage
        File file = new File("Image/OwlImage.jpg");
        Image image = new Image(file.toURI().toString());
        logo.setImage(image);
        name.setText(PlayerNameString);
        name.setFont(Font.font("Comic Sans MS"));
        primaryButton.setFont(Font.font("Comic Sans MS"));
        highScoresButton.setFont(Font.font("Comic Sans MS"));
        optionsButton.setFont(Font.font("Comic Sans MS"));
        nameInputError = new Label("*Character Limit Exceeded");
        nameInputError.setTextFill(Color.RED);
        nameInputError.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 12));
        nameInputError.setLayoutX(name.getLayoutX()-35);
        nameInputError.setLayoutY(name.getLayoutY()-30);
    }

    @FXML
    private void StartGame() throws IOException {
       // if (musicChoice.is)
        changePlayerName();
        if (PlayerNameString.length() == 0) {
            nameInputError.setText("*Input Your Name");
            if (!Pane.getChildren().contains(nameInputError))
                Pane.getChildren().add(nameInputError);
        } else if (PlayerNameString.length() >=5 && PlayerNameString.length() > 0) {
            nameInputError.setText("*Character Limit Exceeded (1-4)");
            Pane.getChildren().add(nameInputError);
        } else if (PlayerNameString.length() < 5) {
            App.setRoot("GameGUI", colorPicker, musicChoice, backGroundImage, PlayerNameString);
        }
    }
    @FXML
    private void SwitchToHighScoresMenu() throws IOException {
        // if (musicChoice.is)
        changePlayerName();
        App.setRoot("HighScores", colorPicker, musicChoice, backGroundImage, PlayerNameString);
    }
    @FXML
    private void SwitchToOptionsMenu() throws IOException {
        changePlayerName();
        App.setRoot("Options", colorPicker, musicChoice, backGroundImage, PlayerNameString);
    }
    @FXML
    private void RemoveLabel(){
        if (Pane.getChildren().contains(nameInputError)){
            Pane.getChildren().remove(nameInputError);
        }
        name.setText("");
    }

    private void changePlayerName(){
        PlayerNameString = name.getText();
    }
    public static void setMusicChoice(ChoiceBox givenMusicChoice){
        musicChoice = givenMusicChoice;
    }
    public static void setColorPicker(ColorPicker givenColorPicker){
        colorPicker = givenColorPicker;
    }
    public static void setBackGroundImage(String givenBackgroundImage){
        backGroundImage = givenBackgroundImage;
    }
    public static void setPlayerName(String playerName){
        if (playerName == null){
            PlayerNameString = "Ex: John (1-4 Char)";
        }else if (playerName != null){
            PlayerNameString = playerName;
        }
    }

    public TextField getNameTextField(){
        return name;
    }


    public static void setParameters(ChoiceBox givenMusicChoice, ColorPicker givenColorPicker, String givenBackGroundImage, String playerName){
        setMusicChoice(givenMusicChoice);
        setColorPicker(givenColorPicker);
        setBackGroundImage(givenBackGroundImage);
        setPlayerName(playerName);
    }
    public static void InitialMainMenuEssentials(){
        setBackGroundImage(null);
        ChoiceBox tmpCBox = new ChoiceBox();
        tmpCBox.setValue("Don't stop Coming");
        setMusicChoice(tmpCBox);
        setPlayerName(null);
        ColorPicker tmpCPicker = new ColorPicker();
        tmpCPicker.setValue(Color.AQUA);
        setColorPicker(tmpCPicker);
    }
}