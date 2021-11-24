package org.example.Model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.example.Controller.Collision;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SpawnEnemies {
    /**
     * The instance of collision from GameGUI. Initialized in the constructor.
     */
    private final Collision collision;
    /**
     * The anchorPane from gameGUI that the enemy ImageViews will be added to. Initialized in the constructor.
     */
    private final AnchorPane anchorPane;
    /**
     * The TimeLine that controls when enemies will spawn.
     */
    private final Timeline tlineEnemyCreation;
    /**
     * The TimeLine that controls the animations of the enemies.
     */
    private final Timeline tlineEnemyAnimation;
    /**
     * The TimeLine that controls the movement of the enemies.
     */
    private final Timeline tlineEnemyMovement;
    /**
     * The array that holds all of the enemies as the are spawned.
     */
    private final Enemy[] enemyArr = new Enemy[10];
    /**
     * The move multiplier. This is set to 1 initially but is increased as time goes on to increase the difficulty
     */
    private double move;
    /**
     * The index of where the enemy needs to be placed in enemyArr
     */
    private int enemyIndex;
    /**
     * This method will update the move multiplier. This is meant to increase over time thus increasing the difficulty
     * over time. As time goes on, the screen, enemies, and powerups will move faster.
     */
    public void SetMove(double move) {
        this.move = move;
    }
    /**
     * The method removeEnemy() will check the position of the X coordinate of the
     * enemy. If the X coordinate is negative (the enemy is off the page) then it
     * will delete the enemy from the arrays and anchorPane. We use (X Position < -200)
     * to ensure the enemy is not visible to the player.
     *
     * @param index: index is the index of the enemy in the arrays.
     */
    private void removeEnemy(int index){
        anchorPane.getChildren().remove(enemyArr[index].getImageView());
        enemyArr[index] = null;
    }
    /**
     * This method sets any newly spawned enemies into the ArrayList.
     *
     * @param enemyIndex: The index to set the enemy.
     * @param enemy:      The Enemy to be set.
     */
    private void setEnemyArrays(int enemyIndex, Enemy enemy){
        enemyArr[enemyIndex] = enemy;
        anchorPane.getChildren().add(enemy.getImageView());
    }
    /**
     * This method is called every 3 seconds, and it will spawn in a random enemy. It chooses which enemy by
     * randomly selecting an integer (from 0-2) and basing its decision off of that number. If randEnemy is 0,
     * it will instantiate a new SpikeEnemy class. If randEnemy is 1, it will instantiate a new ZapperEnemy
     * class and pass it a random Y position in the proper bounds. If randEnemy is 2, it will instantiate a
     * new QuizEnemy class and pass it a random Y position with the proper bounds as well.
     */
    private void InitializeEnemy() {
        // Generates a random number for enemy type and y value.
        Enemy enemy = null;
        //instance of random class
        Random rand = new Random();
        // chooses what enemy
        int randEnemy = rand.nextInt(3);
        switch (randEnemy){
            case 0:
                enemy = new SpikeEnemy();
                break;
            case 1:
                int randomYPosZapper = rand.nextInt((int) Enemy.SCREEN_HEIGHT - 400);
                enemy = new ZapperEnemy(randomYPosZapper);
                break;
            case 2:
                int randomYPosMissile = rand.nextInt((int) Enemy.SCREEN_HEIGHT - 100);
                enemy = new QuizEnemy(randomYPosMissile);
                break;
        }

        // ensuring we don't get an enemy at an index we dont want
        if (enemyIndex == 10) enemyIndex = 0;
        setEnemyArrays(enemyIndex, enemy);
        enemyIndex++;
    }
    /**
     * This method is called every 250 millis-seconds. It will check which picture the enemy ImageView is
     * currently using, then it will change it to the next picture to add an animation. This method is
     * similar to how old cartoons did animation, by quickly moving between images that were slightly
     * different the characters were able to appear as if they were moving. Same concept applies here.
     */
    private void enemyAnimation() {
        for (int i = 0; i < 10; i++){
            Enemy tmpAnimEnemy = enemyArr[i];
            if (tmpAnimEnemy != null) {
                HashMap<Integer, Image> enemyHashMap =  tmpAnimEnemy.getImageHash();
                for (Map.Entry<Integer, Image> enemyIMG: enemyHashMap.entrySet()) {
                    if (tmpAnimEnemy.getEnemyImage().equals(enemyIMG.getValue())) {
                        if (enemyIMG.getKey() == 3) {
                            tmpAnimEnemy.setEnemyImage(1);
                        } else {
                            tmpAnimEnemy.setEnemyImage(enemyIMG.getKey() + 1);
                        }
                        break;
                    }
                }
            }
        }
    }
    /**
     * This method is called every 4 millis-seconds to move the enemy over. It starts by moving the spike and zapper
     * enemies by one pixel, but the quiz missile will be moved over by 3 pixels as it shoots at the enemy faster.
     * This method loops through all enemies and picks how far to move it based on which enemy type the enemy is.
     */
    private void EnemyMovement() {
        for (int i = 0; i < 10; i++) {
            Enemy tmpMoveEnemy = enemyArr[i];
            if (tmpMoveEnemy != null){
                if (tmpMoveEnemy.getEnemyType().equals(Enemy.enemyType.SPIKE) ||
                        tmpMoveEnemy.getEnemyType().equals(Enemy.enemyType.ZAPPER)) {
                    tmpMoveEnemy.getImageView().setX(tmpMoveEnemy.getImageView().getX() - move);
                } else if (tmpMoveEnemy.getEnemyType().equals(Enemy.enemyType.QUIZMISSILE)) {
                    tmpMoveEnemy.getImageView().setX(tmpMoveEnemy.getImageView().getX() - 3 * move);
                }
                if (tmpMoveEnemy.getImageView().getX() < -200)
                    removeEnemy(i);
                CheckEnemyXPos(tmpMoveEnemy);
            }
        }
    }
    /**
     * This method is meant to check if the enemy is at the characters X position. This saves us from having to
     * call collision for every enemy every second. Instead we only call when the X positions match as that's the only
     * time there could be any possible collision.
     *
     * @param enemy: The enemy that must be checked for an X position collision.
     */
    private void CheckEnemyXPos(Enemy enemy) {
        final double playerXLeft = 200;
        final double playerXRight = playerXLeft + 75.6;
        double enemyXLeft = enemy.getImageView().getX();
        double enemyXRight = enemyXLeft + enemy.getImageView().getFitWidth();

        if (enemyXRight <= playerXRight && enemyXRight >= playerXLeft
                || enemyXLeft <= playerXRight && enemyXLeft >= playerXLeft){
            collision.checkEnemyCollision(enemy);
        }
    }
    /**
     * Return the TimeLine for enemy creation.
     *
     * @return The TimeLine for enemy creation.
     */
    public Timeline getTlineEnemyCreation() {
        return tlineEnemyCreation;
    }
    /**
     * Returns the TimeLine for enemy animation.
     *
     * @return The TimeLine for enemy animation.
     */
    public Timeline getTlineEnemyAnimation() {
        return tlineEnemyAnimation;
    }
    /**
     * Returns the TimeLine for enemy movement.
     *
     * @return The TimeLine for enemy movement.
     */
    public Timeline getTlineEnemyMovement() {
        return tlineEnemyMovement;
    }
    /**
     * This constructor establishes the TimeLines necessary to create the enemy spawn, movement, and animation.
     * It receives the anchorPane that the ImageViews will be added to, as well as collision and the move multiplier.
     * The move multiplier will change the difficulty as the game progresses. The longer you play the faster the
     * enemies will have to move. So after some time, the multiplier moves up from 1 to increase how many pixels the
     * enemies will move per 4 millis-seconds.
     *
     * @param anchorPane: The AnchorPane that the enemy ImageViews will be added to.
     * @param collision:  The instance of collision in GameGUI.
     * @param move:       A multiplier that will increase the amount the character moves throughout the game.
     */
    public SpawnEnemies(AnchorPane anchorPane, Collision collision, double move) {
        this.anchorPane = anchorPane;
        this.collision = collision;
        SetMove(move);

        // tmpEnemy Creation time line Spawns enemy every 3 seconds
        tlineEnemyCreation = new Timeline(new KeyFrame(Duration.millis(3000), event -> InitializeEnemy()));
        tlineEnemyCreation.setCycleCount(Timeline.INDEFINITE);
        tlineEnemyCreation.play();
        // tmpEnemy Animation Time line
        tlineEnemyAnimation = new Timeline(new KeyFrame(Duration.millis(250), event -> enemyAnimation()));
        tlineEnemyAnimation.setCycleCount(Timeline.INDEFINITE);
        tlineEnemyAnimation.play();
        // tmpEnemy Movement Time line
        tlineEnemyMovement = new Timeline(new KeyFrame(Duration.millis(4), event -> EnemyMovement()));
        tlineEnemyMovement.setCycleCount(Timeline.INDEFINITE);
        tlineEnemyMovement.play();

    }

}


