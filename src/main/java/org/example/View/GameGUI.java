package org.example.View;

import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.example.Controller.Collision;
import org.example.Controller.Death;
import org.example.Controller.HighScores;
import org.example.Controller.Music;
import org.example.Model.SpawnEnemies;
import org.example.Model.Player;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.Model.Powerups;

public class GameGUI {
  /**
   * The String containing the path of the background image.
   */
  private final String backGroundImageString;
  /**
   * The String containing the players name.
   */
  private final String playerName;
  /**
   * The music the player chose.
   */
  private final Music music;
  /**
   *The color of the background, if the user chose a picture background this will be null.
   */
  private final Color backGroundColor;
  /**
   * One of the two panes attached to the anchorPane that will move.
   */
  private AnchorPane movingPane;
  /**
   * One of the two panes attached to the anchorPane that will move.
   */
  private AnchorPane movingPane2;
  /**
   * The TimeLine that controls the background movement.
   */
  private Timeline tline;
  /**
   * The TimeLine that will gradually increase the move Multiplier.
   */
  private Timeline tline2;
  /**
   * The move multiplier that is initialized to 1.
   */
  private double move = 1;
  /**
   * The SpawnEnemies object that will do all of the enemy spawn, movement, and animation.
   */
  private SpawnEnemies enemySpawns;
  /**
   * The Powerups object that will do all of the power up spawn, movement, and animation.
   */
  private Powerups powerUp;
  /**
   * The PauseMenu object that will add a pause menu to the anchor pane when the player hits 'p'.
   */
  private PauseMenu pauseMenu;
  /**
   * The HighScores object that will start the high scores TimeLine and display the score in the top right corner of
   * the players screen.
   */
  private HighScores highScore;
  //private boolean once = true;
  /**
   * The boolean to tell if the music has been muted.
   */
  private double MutedVol;
  /**
   * The boolean to tell if the game has been paused.
   */
  private boolean isPaused = false;
  /**
   * This method is the heart of this project. It is here to initialize most of the classes, as well as set the
   * scene and stage. Every from Player to HighScores is initialized here. The only things that arent are the
   * subclasses or classes that are instantiated in another class such as QuizEnemy, SpikeEnemy, Enemy, etc. Once
   * this method finishes running the game is ready to be played.
   *
   * @param stage: The stage everything is added it.
   */
  public void Start(Stage stage){

    //setting monitor size
    Rectangle2D screenBounds = Screen.getPrimary().getBounds();
    double screenWidth = screenBounds.getMaxX();
    double screenHeight = screenBounds.getMaxY(); //adjusted second screen size height

    // Initializing stage's properties
    stage.setFullScreen(true);
    stage.setMaxWidth(screenWidth);
    stage.setMaxHeight(screenHeight);
    stage.setResizable(false);

    // Instantiating the Panes
    AnchorPane anchorPane = new AnchorPane();
    anchorPane.setPrefSize(screenWidth,screenHeight);
    anchorPane.setMaxSize(screenWidth, screenHeight);
    MovablePanes(anchorPane);

    // Declaration of any objects
    Player player = new Player(playerName);
    player.initialize(screenHeight);
    anchorPane.getChildren().add(player.getPlayerImageView());
    Death death = new Death(player, screenHeight);
    death.setGameGUI(this);
    Collision collision = new Collision(player, death);
    enemySpawns = new SpawnEnemies(anchorPane, collision, move);
    highScore = new HighScores(anchorPane,playerName, death);
    pauseMenu = new PauseMenu(music);
    powerUp = new Powerups(anchorPane, collision, this.move, screenWidth, screenHeight);

    // Movement Timeline
    tline = new Timeline(new KeyFrame(Duration.millis(4), event -> MovePane(anchorPane.getPrefWidth()))); // millis = 4
    tline.setCycleCount(Timeline.INDEFINITE);
    tline.play();

    // Difficult Timeline
    tline2 = new Timeline(new KeyFrame(Duration.millis(5000), event -> UpdateMove()));
    tline2.setCycleCount(Timeline.INDEFINITE);
    tline2.play();

    // Playing the music
    music.play();

    // Declaration of the Scene
    Scene scene = new Scene(anchorPane);

    //setting the scene
    SetOnKeyPress(scene, player, anchorPane);
    stage.setScene(scene);
    player.setStage();
    stage.show();
  }
  /**
   * This method initializes the moving panes. It seems like it is doing too much but it has to do the same things
   * twice as both panes need to be initialized the exact same except for their initial X pos.
   *
   * @param anchorPane: The AnchorPane that the moving panes will be added to.
   */
  private void MovablePanes(AnchorPane anchorPane){
    this.movingPane = new AnchorPane();
    this.movingPane2 = new AnchorPane();

    movingPane.setPrefSize(anchorPane.getPrefWidth(), anchorPane.getPrefHeight());
    movingPane2.setPrefSize(anchorPane.getPrefWidth(), anchorPane.getPrefHeight());
    movingPane2.setLayoutX(-1 * anchorPane.getPrefWidth());

    GameBackground background = new GameBackground(movingPane, movingPane2, backGroundImageString);
    ImageView backGroundIMG = background.getBackgroundIMG();
    ImageView backGroundIMGFlipped = background.getBackgroundIMGFlipped();
    movingPane.getChildren().add(backGroundIMG);
    movingPane2.getChildren().add(backGroundIMGFlipped);
    //anchorPane.getChildren().add(anchorPaneIMG);

    Color color = backGroundColor;
    if (backGroundImageString == null) {
      movingPane.setBackground(new javafx.scene.layout.Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
      movingPane2.setBackground(new javafx.scene.layout.Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
    }
    anchorPane.getChildren().add(movingPane);
    anchorPane.getChildren().add(movingPane2);
  }
  /**
   * This method is responsible for moving the AnchorPanes to make it appear as though the player is moving forward.
   * If the pane is beyond the players screen then it will set its X pos to the width of his screen. This will allow
   * the pane to continuously move to the right like a treadmill.
   *
   * @param screenWidth: The width of the players screen.
   */
  private void MovePane(double screenWidth) {
    double i = movingPane.getLayoutX();
    double j = movingPane2.getLayoutX();
    double negativeScreenWidth = -1 * screenWidth;
    // Resets first moving pane to the right
    if (i <= negativeScreenWidth) {
      movingPane.setLayoutX(screenWidth-2*move);
      i = movingPane.getLayoutX();
    }
    // Resets second moving pane to the right
    if (j <= negativeScreenWidth) {
      movingPane2.setLayoutX(screenWidth-2*move);
      j = movingPane2.getLayoutX();
    }
    // Moves panes
    if (i > negativeScreenWidth || j > negativeScreenWidth) { //change to >=
      movingPane.setLayoutX(i - move);
      movingPane2.setLayoutX(j - move);
    }
  }
  /**
   * This method will be called to update the move multiplier (see enemy for more incite). It is called
   * every 5 seconds.
   */
  private void UpdateMove() {
    if (move <= 4) {
      move += .1;
      this.enemySpawns.SetMove(move);
      this.powerUp.SetMove(move);
    }
  }
  /**
   * This method sets the OnKeyPressed Event handler for the scene. Allows the user to
   * pause the game and view the a pause menu by pressing the "p" key. The
   * pause menu will display high scores, a volume knob, and a button that
   * returns the player to the main menu. The player can then unpause the
   * game by hitting the "p" key again. The player may also mute the music
   * by pressing the "m" key. By pressing the "m" key again the music will
   * be returned to normal volume.
   */
  private void SetOnKeyPress(Scene scene, Player player, AnchorPane anchorPane){

    scene.setOnKeyPressed(keyEvent -> {
      if (keyEvent.getEventType().equals(KeyEvent.KEY_PRESSED)) {
        if (keyEvent.getCode() == KeyCode.M && music.getVolume()>0){
          MutedVol=music.getVolume();
          music.setVolume(0);
        }else if (keyEvent.getCode() == KeyCode.M && music.getVolume()==0){
          music.setVolume(MutedVol);
        }
        if (keyEvent.getCode() == KeyCode.P) {
          if (!isPaused) {
            isPaused = true;
            tline.pause();
            tline2.pause();
            music.stop();
            enemySpawns.getTlineEnemyCreation().pause();
            enemySpawns.getTlineEnemyAnimation().pause();
            enemySpawns.getTlineEnemyMovement().pause();
            highScore.getHighScoresTLine().pause();
            powerUp.getTlinePowerupCreation().pause();
            powerUp.getTlineAnimate().pause();
            powerUp.getPowerupTimeline().pause();
            player.getTimer().stop();
            anchorPane.getChildren().add(pauseMenu.SetPausePane());
          } else {
            isPaused = false;
            tline.play();
            tline2.play();
            music.play();
            enemySpawns.getTlineEnemyCreation().play();
            enemySpawns.getTlineEnemyAnimation().play();
            enemySpawns.getTlineEnemyMovement().play();
            highScore.getHighScoresTLine().play();
            powerUp.getTlinePowerupCreation().play();
            powerUp.getTlineAnimate().play();
            powerUp.getPowerupTimeline().play();
            player.getTimer().start();
            if (anchorPane.getChildren().contains((pauseMenu.SetPausePane())))
              anchorPane.getChildren().remove(pauseMenu.SetPausePane());
          }
        }
        player.handle(keyEvent);
      }});
    scene.setOnKeyReleased(player);
  }

  /**
   * This method is meant to stop all TimeLines and AnimationTimers so when you end the game
   */
  public void stopTimeLines(){
    tline.stop();
    tline2.stop();
    music.stop();
    enemySpawns.getTlineEnemyCreation().stop();
    enemySpawns.getTlineEnemyAnimation().stop();
    enemySpawns.getTlineEnemyMovement().stop();
    highScore.getHighScoresTLine().stop();
    powerUp.getTlinePowerupCreation().stop();
    powerUp.getTlineAnimate().stop();
    powerUp.getPowerupTimeline().stop();
  }

  /**
   * Returns the Music object.
   * @return The Music object.
   */
  public Music getMusic(){
    return music;
  }

  /**
   * This is the constructor of the GameGUI class. It serves to initialize the background, music choice, player name,
   * and the music volume. Once it has all of these things. It will then play the music.
   * @param backGround:            The color of the background, will be set to null if the player chooses an image background.
   * @param musicChoice:           The choice of music the player chose.
   * @param backGroundImageString: The image background the player chose, will be null if the player chose a color
   *                               background
   * @param playerName:            The name of the player.
   * @param musicVolume:           The chosen volume of the music.
   */
  public GameGUI(Color backGround, String musicChoice, String backGroundImageString, String playerName, double musicVolume) {
    this.backGroundColor = backGround;
    this.backGroundImageString = backGroundImageString;
    this.playerName = playerName;
    this.music = new Music(musicChoice);
    music.setVolume(musicVolume);
  }
}

/*
  SPENCER!! LOL.
  This was in move pane but I think you should create another TimeLine in PowerUps and do all of this. Kind of like
  how I did the TimeLines for enemy movement, animation and creation.


  if (!collision.getIsStop()) {
        if (powerUp.Boost && once){
          tline2.stop();
          move=move*3;
          once=false;
        }
        if (!once && !powerUp.Boost){
          tline2.play();
          move=move/3;
          once=true;
        }
      }
 */
