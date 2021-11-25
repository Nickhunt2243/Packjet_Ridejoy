package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.example.View.GameGUI;
import org.example.View.HighScoresMenu;
import org.example.View.OptionsMenu;

import java.io.File;
import java.io.IOException;

/**
 * JavaFX main.java.org.example.App
 */
public class App extends Application {
    /**
     *
     */
    private static Stage stage;
    /**
     *
     */
    private static Scene scene;
    /**
     *
     */
    private static GameGUI gameGUI = null;
    /**
     *
     */
    private static MediaPlayer mPlayer;
    /**
     *
     */
    private static double volumeDouble = .05;
    /**
     *
     */
    private static ChoiceBox<String> musicChoiceBox = null;
    /**
     *
     */
    private static Color backGroundPicker = null;
    /**
     *
     */
    private static String backGroundImageString = null;
    /**
     *
     */
    private static String playerNameString = null;
    private static boolean hasComeFromOptionsMenu = false;


    public static String getPlayerName() {
        return playerNameString;
    }

    /**
     *  The method that is called from launch in Application that is used to initialize the stage. This is called
     *  only once in the start up of the game.
     *
     * @param stage: The stage the main, options, high scores, and game menu are run on.
     * @throws IOException: In case there is an error with loading the fxml file.
     */
    @Override
    public void start(Stage stage) throws IOException {
        App.stage = stage;
        scene = new Scene(loadFXML("/Model/primary"), 1530.0, 780.0);
        stage.setScene(scene);
        setMedia();
        mPlayer.play();
        setVolume(25);
        stage.show();
    }
    /**
     * This method is used as the guidance between all the possible directions. It will direct the user to where
     * they want to go. If they choose to go the the options menu it will send them to the options menu and
     * initialize what is necessary. If the player wants to start the game, this method initializes an instance
     * of GameGUI and starts the game. It is also responsible for saving any settings the player has set. If
     * the player comes from the OptionsMenu, then the players choice of background color/image, and music choice
     * for the game will be saved.
     *
     *
     * @param fxml: Where the user wants to be directed to.
     * @param backGround: The background color that the user wants to use for the game.
     * @param musicChoice: The choice of music the player has chosen.
     * @param backgroundImage: The file path of the background image for the players selected image.
     * @param playerName: The name the player specifies. Should be 1-4 characters.
     * @throws IOException: In case there is an error with loading any fxml files.
     */
    public static void setRoot(String fxml, Color backGround, ChoiceBox<String> musicChoice,
                               String backgroundImage, String playerName) throws IOException {
        savePlayerName(playerName);
        if (hasComeFromOptionsMenu){
            saveBackGroundColor(backGround);
            saveBackGroundImage(backgroundImage);
            saveMusicChoice(musicChoice);
        }
        stage.setFullScreen(false);
        switch (fxml) {
            case "GameGUI":
                mPlayer.stop();
                stage.hide();
                hasComeFromOptionsMenu = false;
                gameGUI = new GameGUI(backGroundPicker, musicChoiceBox.getValue(), backGroundImageString, playerNameString,
                        volumeDouble);
                gameGUI.Start(stage);
                break;
            case "/Model/primary":
                stage.hide();
                hasComeFromOptionsMenu = false;
                if (gameGUI!=null) gameGUI.getMusic().stop();
                scene = new Scene(loadFXML("/Model/primary"));
                if (mPlayer.getStatus().equals(MediaPlayer.Status.STOPPED)) mPlayer.play();
                stage.setScene(scene);
                stage.show();
                break;
            case "HighScores":
                stage.hide();
                hasComeFromOptionsMenu = false;
                new HighScoresMenu(stage);
                break;
            case "Options":
                stage.hide();
                hasComeFromOptionsMenu = true;
                OptionsMenu options = new OptionsMenu();
                options.start(stage, musicChoiceBox, backGroundPicker, backGroundImageString);
                break;
        }
    }

    /**
     * This method is used to load an fxml file. Mainly just used for the main screen. I chose to not use fxml for
     * the other menus because I think it is more impressive to physically initialize every object to a pane. As
     * a personal project I wanted to show off more of what I could do. So I chose a different approach.
     *
     * @param fxml: The string containing the path to the fxml file.
     * @return The root to the scene to initialize the scene.
     * @throws IOException: If an error occurs during loading.
     */
    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    /**
     * The method that will initialize the music to play Bobby Caldwell's What You Won't Do for Love.
     */
    private static void setMedia(){
        Media sound = new Media(new File("Songs/Bobby Caldwell - What You Won't Do for Love (Album Version).mp3").toURI().toString());
        mPlayer = new MediaPlayer(sound);
    }
    /**
     *  Used to set the volume of the music.
     *
     * @param volume: The volume to change the music to.
     */
    public static void setVolume(double volume){
        volumeDouble = volume;
        mPlayer.setVolume(volume/100);
    }
    /**
     *  Used to get the volume of the music.
     *
     * @return The volume of the music.
     */
    public static double getVolume(){
        return 100* mPlayer.getVolume();
    }
    /**
     *  The method that will save the music choice of the player only if it is not trying to change it to null.
     *
     * @param musicChoice: The music choice the player has chosen.
     */
    private static void saveMusicChoice(ChoiceBox<String> musicChoice) {
        if (musicChoice != null)
            musicChoiceBox = musicChoice;
    }
    /**
     *  The method that will save the background color that the player has chosen only if it is not trying to
     *  change it to null.
     *
     * @param background: The background color to change
     */
    private static void saveBackGroundColor(Color background){
        if (background != null)
            backGroundPicker=background;
    }
    /**
     * The method that saves the players background image file path that they chose.
     *
     * @param backGroundImage: The backGround image file path the player selected.
     *                         If no image is selected then this is set to null.
     */
    private static void saveBackGroundImage(String backGroundImage){
        backGroundImageString=backGroundImage;
    }
    /**
     * The method that saves the players name only if it is not trying to change it to null.
     *
     * @param playerName: The name to change the player to.
     */
    private static void savePlayerName(String playerName){
        if (playerName != null)
            playerNameString=playerName;
    }
    /**
     * This is the main method of the project. It calls Launch() which is a static method in Application.
     *
     * @param args: The arguments passed in from the user(nothing is passed in and even if they are they will
     *              not be used).
     */
    public static void main(String[] args) {
        launch();
    }

}