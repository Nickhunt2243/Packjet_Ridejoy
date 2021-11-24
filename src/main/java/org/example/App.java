package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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



    private static Stage stage;
    private static Scene scene;
    private static GameGUI gameGUI = null;
    private static MediaPlayer mPlayer;
    private static double volumeDouble = .05;
    private static ChoiceBox<String> musicChoiceBox = null;
    private static ColorPicker backGroundPicker = null;
    private static String backGroundImageString = null;
    private static String playerNameString = null;

    @Override
    public void start(Stage stage) throws IOException {
        App.stage = stage;
        MainMenu.InitialMainMenuEssentials();
        scene = new Scene(loadFXML("/Model/primary"), 1530.0, 780.0);
        stage.setScene(scene);
        setMedia();
        mPlayer.play();
        setVolume(25);
        stage.show();
    }

    public static void setRoot(String fxml, ColorPicker backGround, ChoiceBox<String> musicChoice, String backgroundImage, String playerName) throws IOException {
        saveBackGroundColor(backGround);
        saveBackGroundImage(backgroundImage);
        saveMusicChoice(musicChoice);
        savePlayerName(playerName);
        stage.setFullScreen(false);
        switch (fxml) {
            case "GameGUI":
                mPlayer.stop();
                stage.hide();
                gameGUI = new GameGUI(backGroundPicker, musicChoiceBox.getValue(), backGroundImageString, playerNameString,
                        volumeDouble);
                gameGUI.Start(stage);
                break;
            case "/Model/primary":
                stage.hide();
                if (gameGUI!=null) gameGUI.getMusic().stop();
                scene = new Scene(loadFXML("/Model/primary"));
                MainMenu.setParameters(musicChoiceBox, backGroundPicker, backGroundImageString, playerNameString);
                if (mPlayer.getStatus().equals(MediaPlayer.Status.STOPPED)) mPlayer.play();
                stage.setScene(scene);
                stage.show();
                break;
            case "HighScores":
                stage.hide();
                HighScoresMenu highScoresMenu = new HighScoresMenu(stage, backGroundImageString);
                highScoresMenu.HighScoresStart();
                break;
            case "Options":
                stage.hide();
                OptionsMenu options = new OptionsMenu();
                options.start(stage, musicChoiceBox, backGroundPicker, backGroundImageString);
                break;
        }
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    private static void setMedia(){
        Media sound = new Media(new File("Songs/Bobby Caldwell - What You Won't Do for Love (Album Version).mp3").toURI().toString());
        mPlayer = new MediaPlayer(sound);
    }
    public static void setVolume(double volume){
        volumeDouble = volume;
        mPlayer.setVolume(volume/100);
    }
    public static double getVolume(){
        return 100* mPlayer.getVolume();
    }

    private static void saveMusicChoice(ChoiceBox<String> musicChoice) {
        if (musicChoice != null)
            musicChoiceBox = musicChoice;
    }
    private static void saveBackGroundColor(ColorPicker background){
        if (background != null)
            backGroundPicker=background;
    }
    private static void saveBackGroundImage(String backGroundImage){
        if (backGroundImage != null)
            backGroundImageString=backGroundImage;
    }
    private static void savePlayerName(String playerName){
        if (playerName != null)
            playerNameString=playerName;
    }



    public static void main(String[] args) {
        launch();
    }

}