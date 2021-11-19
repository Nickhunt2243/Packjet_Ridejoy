package org.example.Controller;

import org.example.Model.Enemy;
import org.example.Model.Player;
import org.example.Model.Powerups;


public class Collision {
    /**
     * The instance of Death instantiated in GameGUI.
     */
    private final Death death;
    /**
     * The instance of Player instantiated in GameGUI.
     */
    private final Player player;
    /**
     * Leave for Spencer lol.
     */
//    public boolean getIsStop(){
//        return false;
//    }
    private Powerups powerUp;
    /**
     * This method is meant to check the enemy collision with the player. We start by assigning the hit box variables.
     * This makes the if statement a lot easier to digest. We get the bottom hit box by adding the height of the
     * image to the Y position. From there we check if the players bottom or top hit boxes are within the enemy's
     * hit box range. If it is we check to see if the enemy acquired any power ups, if it hasn't then we start the
     * deathSequence method from the Death class.
     *
     * @param enemy: The enemy to be checked.
     */
    public void checkEnemyCollision(Enemy enemy) {
        final double playerTopHitbox = player.getPlayerImageView().getY();
        final double playerBottomHitBox = playerTopHitbox + player.getPlayerImageView().getFitHeight();
        final double enemyTopHitBox = enemy.getImageView().getY();
        final double enemyBottomHitBox = enemyTopHitBox + enemy.getImageView().getFitHeight();

        if (playerTopHitbox <= enemyBottomHitBox && playerTopHitbox >= enemyTopHitBox ||
                playerBottomHitBox >= enemyBottomHitBox && playerBottomHitBox >= enemyTopHitBox) {
            if (powerUp != null) {
                if (!powerUp.isPowerup) {
                    death.deathSequence();
                } else if (powerUp.Shield) {
                    powerUp.PowerupTimerShield();
                }
            } else
                death.deathSequence();
        }
    }

    /**
     * Leave for Spencer.
     */
    public void checkPowerCollision(){
        if (powerUp != null && (powerUp.ShowBoost != null || powerUp.ShowShield != null)){
            if (powerUp.PowerupType == 0){
                BoostPowerUpCollisionBox();
            }else if (powerUp.PowerupType == 1){
                ShieldPowerUpCollisionBox();
            }
        }
    }
    /**
     * Leave for Spencer.
     */
    private void BoostPowerUpCollisionBox(){
        double playerCenterY = player.getPlayerImageView().getY() + player.getPlayerImageView().getFitHeight() / 2;
        double BoostCenterY = powerUp.ShowBoost.getY() + powerUp.ShowBoost.getFitHeight() / 2;
        double BoostYDelta = 50;

        if (playerCenterY >= BoostCenterY - BoostYDelta && playerCenterY <= BoostCenterY + BoostYDelta) {
            powerUp.PowerupTimer();
        }
    }

    /**
     * Leave for Spencer.
     */
    private void ShieldPowerUpCollisionBox(){
        double playerCenterY = player.getPlayerImageView().getY() + player.getPlayerImageView().getFitHeight() / 2;
        double ShieldCenterY = powerUp.ShowShield.getY() + powerUp.ShowShield.getFitHeight() / 2;
        double BoostYDelta = 50;

        if (playerCenterY >= ShieldCenterY- BoostYDelta && playerCenterY <= ShieldCenterY+ BoostYDelta) {
            powerUp.isPowerup=true;
            powerUp.Shield=true;
        }
    }
    /**
     * The constructor of Collision will take in the Player and Death classes respectively from GameGUI.
     *
     * @param player: The instance of Player from GameGUI.
     * @param death: The instance of Death from GameGUI.
     */
    public Collision(Player player, Death death){
        this.player = player;
        this.death = death;
    }
}