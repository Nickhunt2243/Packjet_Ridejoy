package org.example.View;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;

public class GameBackground {

  private final String backGroundImageString;
  private final AnchorPane movingPane;
  private final AnchorPane movingPane2;

  private double screenWidth;
  private double screenHeight;
  private ImageView SkyBackgroundIMG;
  private ImageView retroBackgroundIMG;
  private ImageView forestBackgroundIMG;
  private ImageView snowBackgroundIMG;
  private ImageView FloorIMG;
  private ImageView FloorIMGFlipped;
  private ImageView snowBackgroundIMGFlipped;
  private ImageView forestBackgroundIMGFlipped;
  private ImageView retroBackgroundIMGFlipped;
  private ImageView SkyBackgroundIMGFlipped;

  public GameBackground(AnchorPane movingPane, AnchorPane movingPane2, String backGroundImageString){
    this.movingPane = movingPane;
    this.movingPane2 = movingPane2;
    this.backGroundImageString = backGroundImageString;
    this.screenWidth = movingPane.getPrefWidth();
    this.screenHeight = movingPane.getPrefHeight();
  }

  public ImageView getBackgroundIMG(){

    if (backGroundImageString == null){
      InitializeFloor();
      return FloorIMG;
    }
    if (backGroundImageString.equals("snow")){
      InitializeSnow();
      return snowBackgroundIMG;
    }
    if (backGroundImageString.equals("forest")){
      InitializeForest();
      return forestBackgroundIMG;
    }
    if(backGroundImageString.equals("retro")){
      InitializeRetro();
      return retroBackgroundIMG;
    }
    if (backGroundImageString.equals("sky")){
      InitializeSky();
      return SkyBackgroundIMG;
    }
    return null;
  }

  public ImageView getBackgroundIMGFlipped(){

    if (backGroundImageString == null){
      InitializeFloorFlipped();
      return FloorIMGFlipped;
    }
    if (backGroundImageString.equals("snow")){
      InitializeSnowFlipped();
      return snowBackgroundIMGFlipped;
    }
    if (backGroundImageString.equals("forest")){
      InitializeForestFlipped();
      return forestBackgroundIMGFlipped;
    }
    if(backGroundImageString.equals("retro")){
      InitializeRetroFlipped();
      return retroBackgroundIMGFlipped;
    }
    if (backGroundImageString.equals("sky")){
      InitializeSkyFlipped();
      return SkyBackgroundIMGFlipped;
    }
    return null;
  }

  private ImageView InitializeSky(){
    Image sky = new Image("file:Image/Sky.png");
    SkyBackgroundIMG = new ImageView(sky);
    SkyBackgroundIMG.setFitWidth(screenWidth+5);
    SkyBackgroundIMG.setFitHeight(screenHeight);
    SkyBackgroundIMG.setX(0);
    SkyBackgroundIMG.setY(0);
    return SkyBackgroundIMG;
  }

  private ImageView InitializeRetro() {
    Image retro = new Image("file:Image/Retro.png");
    retroBackgroundIMG = new ImageView(retro);
    retroBackgroundIMG.setFitWidth(screenWidth+5);
    retroBackgroundIMG.setFitHeight(screenHeight);
    retroBackgroundIMG.setLayoutX(0);
    retroBackgroundIMG.setLayoutY(0);
    return retroBackgroundIMG;
  }

  private ImageView InitializeForest() {
    Image forest = new Image("file:Image/Forest.png");
    forestBackgroundIMG = new ImageView(forest);
    forestBackgroundIMG.setFitWidth(screenWidth+5);
    forestBackgroundIMG.setFitHeight(screenHeight);
    forestBackgroundIMG.setLayoutX(0);
    forestBackgroundIMG.setLayoutY(0);
    return forestBackgroundIMG;
  }

  private ImageView InitializeSnow() {
    Image snow = new Image("file:Image/Snow.png");
    snowBackgroundIMG = new ImageView(snow);
    snowBackgroundIMG.setFitWidth(screenWidth+5);
    snowBackgroundIMG.setFitHeight(screenHeight);
    snowBackgroundIMG.setX(0);
    snowBackgroundIMG.setY(0);
    return snowBackgroundIMG;
  }

  public ImageView InitializeFloor() {
    Image floor = new Image("file:Image/Floor.png");
    FloorIMG = new ImageView(floor);
    FloorIMG.setFitWidth(screenWidth+5);
    FloorIMG.setScaleY(2);
    FloorIMG.setX(0);
    FloorIMG.setY(screenHeight-floor.getHeight());
    return FloorIMG;
  }
  private ImageView InitializeSkyFlipped(){
    Image skyFlipped = new Image("file:Image/SkyFlipped.png");
    SkyBackgroundIMGFlipped = new ImageView(skyFlipped);
    SkyBackgroundIMGFlipped.setFitWidth(screenWidth+5);
    SkyBackgroundIMGFlipped.setFitHeight(screenHeight);
    SkyBackgroundIMGFlipped.setX(0);
    SkyBackgroundIMGFlipped.setY(0);
    return SkyBackgroundIMGFlipped;
  }

  private ImageView InitializeRetroFlipped() {
    Image retroFlipped = new Image("file:Image/RetroFlipped.png");
    retroBackgroundIMGFlipped = new ImageView(retroFlipped);
    retroBackgroundIMGFlipped.setFitWidth(screenWidth+5);
    retroBackgroundIMGFlipped.setFitHeight(screenHeight);
    retroBackgroundIMGFlipped.setLayoutX(0);
    retroBackgroundIMGFlipped.setLayoutY(0);
    return retroBackgroundIMGFlipped;
  }

  private ImageView InitializeForestFlipped() {
    Image forestFlipped = new Image("file:Image/ForestFlipped.png");
    forestBackgroundIMGFlipped = new ImageView(forestFlipped);
    forestBackgroundIMGFlipped.setFitWidth(screenWidth+5);
    forestBackgroundIMGFlipped.setFitHeight(screenHeight);
    forestBackgroundIMGFlipped.setLayoutX(0);
    forestBackgroundIMGFlipped.setLayoutY(0);
    return forestBackgroundIMGFlipped;
  }

  private ImageView InitializeSnowFlipped() {
    Image snowFlipped = new Image("file:Image/SnowFlipped.png");
    snowBackgroundIMGFlipped = new ImageView(snowFlipped);
    snowBackgroundIMGFlipped.setFitWidth(screenWidth+5);
    snowBackgroundIMGFlipped.setFitHeight(screenHeight);
    snowBackgroundIMGFlipped.setX(0);
    snowBackgroundIMGFlipped.setY(0);
    return snowBackgroundIMGFlipped;
  }

  public ImageView InitializeFloorFlipped() {
    Image floorFlipped = new Image("file:Image/FloorFlipped.png");
    FloorIMGFlipped = new ImageView(floorFlipped);
    FloorIMGFlipped.setFitWidth(screenWidth+5);
    FloorIMGFlipped.setScaleY(2);
    FloorIMGFlipped.setX(0); //change back to 20
    FloorIMGFlipped.setY(screenHeight-floorFlipped.getHeight());
    return FloorIMGFlipped;
  }
}



