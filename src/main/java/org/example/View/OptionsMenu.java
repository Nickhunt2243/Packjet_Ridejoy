package org.example.View;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.example.App;
import java.io.IOException;


public class OptionsMenu {


    /**
     * The ChoiceBox initialized for the choice of music.
     */
    private ChoiceBox<String> musicChoice;
    /**
     * The colorPicker initialized for the background color. Used if the player does not pick any image background.
     */
    private ColorPicker colorPicker;
    /**
     * The width of the screen of the user.
     */
    private double screenWidth;
    /**
     * The height of the screen of the user.
     */
    private double screenHeight;
    /**
     * The Pane that every Object is added to. This is the root of the Scene.
     */
    private AnchorPane optionsPane;
    /**
     * The snowBackground ImageView.
     */
    private ImageView snowBackgroundIMG;
    /**
     * The forestBackground ImageView.
     */
    private ImageView forestBackgroundIMG;
    /**
     * The retroBackground ImageView.
     */
    private ImageView retroBackgroundIMG;
    /**
     * The skyBackground ImageView.
     */
    private ImageView skyBackgroundIMG;
    /**
     * The snowBackground ImageView.
     */
    private RadioButton snowBackground;
    /**
     * THe forestBackground's radio Button.
     */
    private RadioButton forestBackground;
    /**
     * The retroBackground's radio Button.
     */
    private RadioButton retroBackground;
    /**
     * The skyBackground's radio Button.
     */
    private RadioButton skyBackground;
    /**
     * The radio Button to choose a colored background.
     */
    private RadioButton solidBackground;
    /**
     * The string of the background Image the player specified. Used to carry the players choice to their game.
     */
    private String backgroundIMG;
    /**
     * The colorPicker carried in from the Main menu. This is used in case the user wants to go back into the options
     * menu so his setting previously set are not changed.
     */
    private ColorPicker background;
    /**
     * The music ChoiceBox taken in from the the Main menu. This is used in case the user wants to go back into the
     * options menu so his setting previously set are not changed.
     */
    private ChoiceBox<String> musicChoiceBox;

    /**
     * This will initialize the options pane with preferred size and background color.
     * Then it establishes the screen width and height
     */
    private void InitializePane(){
        optionsPane = new AnchorPane();
        optionsPane.setPrefSize(1530,780);
        optionsPane.setMaxSize(1530,780);
        optionsPane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        screenWidth = optionsPane.getPrefWidth();
        screenHeight = optionsPane.getPrefHeight();
    }

    /**
     * This method initializes the music choice box. It allows the player to pick what music they want to play
     * throughout the game. It gives the user three options: Don't Stop Coming, Little Einsteins (Trap remix),
     * and Wii Sports (Chill-Step remix).
     */
    private void InitializeMusicChoice(){
        musicChoice = new ChoiceBox<>();
        String[] st = {"Don't Stop Coming", "Little Einsteins (Trap remix)", "Wii Sports (Chill-Step remix)"};
        if (musicChoiceBox == null) {
            musicChoice.setValue("Don't Stop Coming");
        } else {
            musicChoice.setValue(musicChoiceBox.getValue());
        }
        musicChoice.setItems(FXCollections.observableArrayList(st));
        musicChoice.setPrefSize(150,30);
        musicChoice.setScaleX(1.5);
        musicChoice.setScaleY(1.5);
        musicChoice.setStyle("-fx-font: 12px \"Comic Sans MS\";");
        musicChoice.setLayoutX(screenWidth/2+10);
        musicChoice.setLayoutY(50-musicChoice.getPrefHeight()/2);
        optionsPane.getChildren().add(musicChoice);
    }

    /**
     * This method initialized the radio buttons for the image backgrounds. It initializes the toggle group and has
     * an onAction event to blur and disable the unselected option, either image background or color background.
     * It will also use the radio buttons to decide which options are selected. There are two toggle groups, one for
     * the image and color background options and one for the images themselves as you dont want to select more that
     * one image at a time. Once all of this has been initialized these radio buttons are added to the OptionsPane.
     */
    private void InitializeBackGroundRadios(){
        solidBackground = new RadioButton();
        RadioButton imageBackground = new RadioButton();
        ToggleGroup BackgroundSelectorGroup = new ToggleGroup();
        solidBackground.setToggleGroup(BackgroundSelectorGroup);
        imageBackground.setToggleGroup(BackgroundSelectorGroup);
        solidBackground.setLayoutX(colorPicker.getLayoutX()-55);
        solidBackground.setLayoutY(42.5);


        Label colorPickerLabel = new Label("Solid GameBackground:");
        colorPickerLabel.setPrefSize(190,20);
        colorPickerLabel.setLayoutX(solidBackground.getLayoutX()-colorPickerLabel.getPrefWidth());
        colorPickerLabel.setLayoutY(40);
        colorPickerLabel.setFont(Font.font("Comic Sans MS", 17));
        colorPickerLabel.setTextFill(Color.WHITE);

        imageBackground.setLayoutX(colorPickerLabel.getLayoutX() - 25);
        imageBackground.setLayoutY(42.5);

        Label imageRadioLabel = new Label("Image GameBackground:");
        imageRadioLabel.setPrefSize(200,20);
        imageRadioLabel.setLayoutX(imageBackground.getLayoutX()-imageRadioLabel.getPrefWidth());
        imageRadioLabel.setLayoutY(40);
        imageRadioLabel.setFont(Font.font("Comic Sans MS", 17));
        imageRadioLabel.setTextFill(Color.WHITE);


        snowBackground = new RadioButton();
        forestBackground = new RadioButton();
        retroBackground = new RadioButton();
        skyBackground = new RadioButton();


        ToggleGroup ImageSelectorGroup = new ToggleGroup();
        snowBackground.setToggleGroup(ImageSelectorGroup);
        forestBackground.setToggleGroup(ImageSelectorGroup);
        retroBackground.setToggleGroup(ImageSelectorGroup);
        skyBackground.setToggleGroup(ImageSelectorGroup);

        snowBackground.setLayoutX(snowBackgroundIMG.getLayoutX()-25);
        snowBackground.setLayoutY(snowBackgroundIMG.getLayoutY());
        forestBackground.setLayoutX(forestBackgroundIMG.getLayoutX()-25);
        forestBackground.setLayoutY(forestBackgroundIMG.getLayoutY());
        retroBackground.setLayoutX(retroBackgroundIMG.getLayoutX()-25);
        retroBackground.setLayoutY(retroBackgroundIMG.getLayoutY());
        skyBackground.setLayoutX(skyBackgroundIMG.getLayoutX()-25);
        skyBackground.setLayoutY(skyBackgroundIMG.getLayoutY());

        if (backgroundIMG == null){
            solidBackground.setSelected(true);
            imageBackground.setSelected(false);
            snowBackgroundIMG.setOpacity(.5);
            forestBackgroundIMG.setOpacity(.5);
            retroBackgroundIMG.setOpacity(.5);
            skyBackgroundIMG.setOpacity(.5);
            snowBackground.setDisable(true);
            forestBackground.setDisable(true);
            retroBackground.setDisable(true);
            skyBackground.setDisable(true);
        } else {
            solidBackground.setSelected(false);
            imageBackground.setSelected(true);
            colorPicker.setDisable(true);
            colorPicker.setOpacity(.5);
            if (backgroundIMG.equals("snow")){
                snowBackground.setSelected(true);
            }
            if (backgroundIMG.equals("forest")){
                forestBackground.setSelected(true);
            }
            if (backgroundIMG.equals("retro")){
                retroBackground.setSelected(true);
            }
            if (backgroundIMG.equals("sky")){
                skyBackground.setSelected(true);
            }
        }

        solidBackground.setOnAction((event -> {
            if (solidBackground.isSelected()){
                colorPicker.setDisable(false);
                colorPicker.setOpacity(1);
                snowBackground.setDisable(true);
                snowBackgroundIMG.setOpacity(.5);
                forestBackground.setDisable(true);
                forestBackgroundIMG.setOpacity(.5);
                retroBackground.setDisable(true);
                retroBackgroundIMG.setOpacity(.5);
                skyBackground.setDisable(true);
                skyBackgroundIMG.setOpacity(.5);
            }
        }));
        imageBackground.setOnAction((event -> {
            colorPicker.setDisable(true);
            colorPicker.setOpacity(.5);
            snowBackground.setDisable(false);
            snowBackgroundIMG.setOpacity(1);
            forestBackground.setDisable(false);
            forestBackgroundIMG.setOpacity(1);
            retroBackground.setDisable(false);
            retroBackgroundIMG.setOpacity(1);
            skyBackground.setDisable(false);
            skyBackgroundIMG.setOpacity(1);
        }));
        optionsPane.getChildren().addAll(solidBackground, imageBackground,colorPickerLabel, imageRadioLabel,snowBackground
        ,forestBackground,retroBackground,skyBackground);
    }

    /**
     * This method initializes the volume slider for the game. This has an action event to reduce or raise the
     * volume accordingly as the player moves the slider.
     */
    private void InitializeVolumeSlider(){
        Slider volume = new Slider(0,100,App.getVolume());
        volume.setPrefSize(150,30);
        volume.setShowTickMarks(true);
        volume.setShowTickLabels(true);
        volume.setScaleX(1.2);
        volume.setLayoutY(1.2);
        volume.setMajorTickUnit(50);
        volume.setBlockIncrement(5f);
        volume.setLayoutX(musicChoice.getLayoutX()+musicChoice.getPrefWidth()+150);
        volume.setLayoutY(50-volume.getPrefHeight()/2+5);
        volume.setOrientation(Orientation.HORIZONTAL);
        volume.valueProperty().addListener((observable, oldValue, newValue) -> App.setVolume(newValue.doubleValue()));

        Label volumeLabel = new Label("Volume: ");
        volumeLabel.setPrefSize(75,20);
        volumeLabel.setLayoutX(volume.getLayoutX()-volumeLabel.getPrefWidth()-10);
        volumeLabel.setLayoutY(40);
        volumeLabel.setFont(Font.font("Comic Sans MS", 17));
        volumeLabel.setTextFill(Color.WHITE);

        optionsPane.getChildren().addAll(volume, volumeLabel);
    }

    /**
     * This method initializes the colorPicker for the colored background. The player may go through an select any
     * color they wish. Once the ColorPicker is initialized it is added to the OptionsPane.
     */
    private void InitializeColorPicker(){
        colorPicker = new ColorPicker();
        colorPicker.setEditable(true);
        if (backgroundIMG == null)
            colorPicker.setValue(background.getValue());
        if (backgroundIMG != null)
            colorPicker.setValue(Color.AQUA);
        colorPicker.setPrefSize(125,30);
        colorPicker.setScaleX(1.5);
        colorPicker.setScaleY(1.5);
        colorPicker.setStyle("-fx-font: 12px \"Comic Sans MS\";");
        colorPicker.setLayoutX(screenWidth/2-((1.5)*colorPicker.getPrefWidth())-10);
        colorPicker.setLayoutY(50-colorPicker.getPrefHeight()/2);
        optionsPane.getChildren().add(colorPicker);
    }

    /**
     * This method initializes the ImageViews for the image backgrounds. It will display the images as a 2 x 2 display.
     * There are four possible choices for a background. The image positions are set next to their radio buttons and
     * then they are added to the OptionsPane.
     */
    private void InitializeImageViews(){
        Image SnowBackground = new Image("file:Image/Snow.png");
        snowBackgroundIMG = new ImageView(SnowBackground);
        snowBackgroundIMG.setFitWidth(screenWidth*3/8);
        snowBackgroundIMG.setFitHeight(screenHeight*3/8);
        snowBackgroundIMG.setLayoutX(screenWidth/8);
        snowBackgroundIMG.setLayoutY(100);


        Image ForestBackground = new Image("file:Image/Forest.png");
        forestBackgroundIMG = new ImageView(ForestBackground);
        forestBackgroundIMG.setFitWidth(screenWidth*3/8);
        forestBackgroundIMG.setFitHeight(screenHeight*3/8);
        forestBackgroundIMG.setLayoutX(snowBackgroundIMG.getLayoutX()+3*screenWidth/8+50);
        forestBackgroundIMG.setLayoutY(100);


        Image RetroBackground = new Image("file:Image/Retro.png");
        retroBackgroundIMG = new ImageView(RetroBackground);
        retroBackgroundIMG.setFitWidth(screenWidth*3/8);
        retroBackgroundIMG.setFitHeight(screenHeight*3/8);
        retroBackgroundIMG.setLayoutX(screenWidth/8);
        retroBackgroundIMG.setLayoutY(100+3*screenHeight/8);


        Image SkyBackground = new Image("file:Image/Sky.png");
        skyBackgroundIMG = new ImageView(SkyBackground);
        skyBackgroundIMG.setFitWidth(screenWidth*3/8);
        skyBackgroundIMG.setFitHeight(screenHeight*3/8);
        skyBackgroundIMG.setLayoutX(retroBackgroundIMG.getLayoutX()+3*screenWidth/8+50);
        skyBackgroundIMG.setLayoutY(100+3*screenHeight/8);

        optionsPane.getChildren().addAll(snowBackgroundIMG, forestBackgroundIMG, retroBackgroundIMG, skyBackgroundIMG);
    }

    /**
     * This method is the return from the Options Menu method. This initialized a "Main Menu" button that will return
     * you to the MainMenu. It chooses the proper background image to send back based on what radio button is selected.
     * Taking solid background as the priority over the image background. So if an image background is selected but
     * the solidBackground button is also selected it will notice the player previously selected an image background but
     * now wants a solid background.
     */
    private void InitializeMainMenuButton(){
        Button mainMenu = new Button("Main Menu");
        mainMenu.setPrefSize(112,30);
        mainMenu.setOnAction(e -> {

            try {
                if (solidBackground.isSelected()) {
                    App.setRoot("/Model/primary", colorPicker, musicChoice,  null, null);
                } else if (snowBackground.isSelected()) {
                    App.setRoot("/Model/primary", colorPicker, musicChoice, "snow", null);
                } else if (forestBackground.isSelected()){
                    App.setRoot("/Model/primary", colorPicker, musicChoice, "forest", null);
                } else if (retroBackground.isSelected()){
                    App.setRoot("/Model/primary", colorPicker, musicChoice, "retro", null);
                } else if (skyBackground.isSelected()){
                    App.setRoot("/Model/primary", colorPicker, musicChoice, "sky", null);
                }

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        mainMenu.setFont(Font.font("Comic Sans MS", 17));

        mainMenu.setLayoutX(forestBackgroundIMG.getLayoutX()+forestBackgroundIMG.getFitWidth()-mainMenu.getPrefWidth());
        mainMenu.setLayoutY(50-mainMenu.getPrefHeight()/2);
        optionsPane.getChildren().add(mainMenu);
    }
    /**
     *  This method is used to initialize the objects on the Main menu. It calls many various helper methods to
     *  complete many tedious tasks. A lot of the object were manually set based on the bounds of the pain. Thus we
     *  made it all more readable by using the helper functions.
     *
     * @param stage:         The stage passed from the App class. After we set a new scene the stage is shown again
     *                       and the options menu is displayed.
     * @param musicChoice:   The choice of music the player has picked. This is initialized to "Don't Stop Coming".
     * @param background:    The background that the player initially picked. This is initialized to the color "Cyan".
     * @param backgroundIMG: The background Image that the player initially picked. This is initialized to null;
     */
    public void start(Stage stage, ChoiceBox<String> musicChoice, ColorPicker background, String backgroundIMG){
        this.musicChoiceBox = musicChoice;
        this.background = background;
        this.backgroundIMG = backgroundIMG;
        InitializePane();
        InitializeMusicChoice();
        InitializeImageViews();
        InitializeColorPicker();
        InitializeBackGroundRadios();
        InitializeMainMenuButton();
        InitializeVolumeSlider();


        Scene scene = new Scene(optionsPane);

        stage.setScene(scene);
        stage.show();
    }
}
