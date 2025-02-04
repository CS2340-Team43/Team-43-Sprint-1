package com.example.demo_2340;

import com.example.demo_2340.CollisionObserver.CollisionObserver;
import com.example.demo_2340.Player_Movement.MovementStrategyPattern;

/**
 * Player class to represent the singleton player
 */
public class Player implements CollisionObserver {
    private static Player instance = null;
    private MovementStrategyPattern movementStrategy;
    private double xPosition;
    private double yPosition;
    private int health;  // Added health variable

    private double speed;

    private boolean isAttackOnCooldown = false;

    /**
     * Base Constructor for singleton player
     */
    private Player() {
        // Initialize default position and health
        this.xPosition = 0;
        this.yPosition = 0;
        this.health = 100; // Set initial health
        this.speed = 1;
    }

    /**
     * getInstance Method
     * @return static player and creates if doesn't exist
     */
    public static Player getInstance() {
        if (instance == null) {
            instance = new Player();
        }
        return instance;
    }

    // Method to create a new instance with the same properties
    public Player clonePlayer() {
        Player clonedPlayer = new Player();
        clonedPlayer.setxPosition(instance.xPosition);
        clonedPlayer.setyPosition(instance.yPosition);
        clonedPlayer.setHealth(instance.health);
        clonedPlayer.setSpeed(instance.speed);
        return clonedPlayer;
    }

    /**
     * positionGetter
     * @return int position
     */
    public double getxPosition() {
        return xPosition;
    }

    /**
     * positionSetter for X
     * @param xPosition int for movement
     */
    public void setxPosition(double xPosition) {
        this.xPosition = xPosition;
    }

    /**
     * Getter for y Position
     * @return int representing y position
     */
    public double getyPosition() {
        return yPosition;
    }

    /**
     * setter for y position
     * @param yPosition int
     */
    public void setyPosition(double yPosition) {
        this.yPosition = yPosition;
    }

    /**
     * movementStrategy Setter
     * @param movementStrategy to represent pattern
     */
    public void setMovementStrategy(MovementStrategyPattern movementStrategy) {
        this.movementStrategy = movementStrategy;
    }

    /**
     * onCollisionDetection method
     * @param damage amount to hurt player
     */
    @Override
    public void onCollisionDetected(int damage) {
        // Handle collision
        // For simplicity, let's assume each collision deducts 10 health point

        // Deduct health
        health -= damage;

        // Check if player has run out of health
        if (health <= 0) {
            // Perform actions when the player is out of health (e.g., end the game)
            endGame();
        }
    }

    /**
     * health getter
     * @return int for health
     */
    public int getHealth() {
        return health;
    }

    /**
     * setter for health
     * @param int representing health
     */
    public void setHealth(int h) {
        health = h;
    }

    /**
     * getter for Speed
     * @return int speed
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * setter for speed
     * @param speed int for speed
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    /**
     * endGame Method
     */
    private void endGame() {

    }

    public boolean isAttackOnCooldown() {
        return isAttackOnCooldown;
    }

    public void setAttackCooldown(boolean cooldown) {
        isAttackOnCooldown = cooldown;
    }
}
