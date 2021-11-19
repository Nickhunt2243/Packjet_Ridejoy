package org.example.Controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Music {

    private MediaPlayer mPlayer;

    public Music(String musicChoice){
        this.mPlayer = pickSong(musicChoice);
    }

    public void setVolume(double vol) {
        mPlayer.setVolume(vol/100);
    }
    public double getVolume(){
        return mPlayer.getVolume()*100;
    }
    public void play(){
        mPlayer.play();
    }
    public void stop(){
        mPlayer.stop();
    }

    public MediaPlayer pickSong(String songChoiceName){
        String SongName="";
        switch (songChoiceName) {
            case "WAP":
                SongName = "Songs/CardiBWAP.mp3";
                break;
            case "Don't stop Coming":
                SongName = "Songs/DontStopComing.mp3";
                break;
            case "Little Einsteins (Trap remix)":
                SongName = "Songs/LittleEinsteins.mp3";
                break;
            case "Wii Sports (Chill-Step remix)":
                SongName = "Songs/WiiRemix.mp3";
                break;
            case "What You Won't Do for Love":
                SongName = "Songs/Bobby Caldwell - What You Won't Do for Love (Album Version).mp3";
                break;
        }
        Media sound = new Media(new File(SongName).toURI().toString());
        mPlayer = new MediaPlayer(sound);
        return mPlayer;
    }
}
