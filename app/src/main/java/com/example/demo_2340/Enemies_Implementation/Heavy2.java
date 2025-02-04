package com.example.demo_2340.Enemies_Implementation;

import android.content.res.Resources;

import com.example.demo_2340.CollisionObserver.CollisionObserver;
//heavy2 file
public class Heavy2 implements Enemies, CollisionObserver {
    //relevant variables to the class
    private String type;
    private double initialXPosition;
    private double initialYPosition;
    private double xPosition;
    private double yPosition;
    private double initialxcord = 0;
    private double initialycord = 0;
    //constructor to set the relavent variables
    public Heavy2() {
        type = "Heavy2";
        this.initialXPosition = initialxcord;
        this.initialYPosition = initialycord;
        this.xPosition = initialXPosition;
        this.yPosition = initialYPosition;
    }
    //getters and setters for the variabels, opverriding the interface
    @Override
    public String getType() {
        return type;
    }

    @Override
    public double move() {
        // Always move downward by a fixed amount
        double downwardMovement = 1.0;

        yPosition = initialYPosition + downwardMovement;

        return yPosition;
    }

    @Override
    public double getxPosition() {
        return xPosition;
    }

    @Override
    public void setxPosition(double xPosition) {
        this.xPosition = xPosition;
    }

    @Override
    public double getyPosition() {
        return yPosition;
    }

    @Override
    public void setyPosition(double yPosition) {
        this.yPosition = yPosition;
    }

    @Override
    public double getDamage() {
        return 5;
    }

    @Override
    public void onCollisionDetected(int damage) {
        // Handle collision
    }

    public void setInitialPosition(double initialX, double initialY) {
        this.initialXPosition = initialX;
        this.initialYPosition = initialY;
        this.xPosition = initialX;
        this.yPosition = initialY;
    }
    @Override
    public void attack() {
        int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

        // Set the position to the bottom left of the screen
        this.xPosition = 0;
        this.yPosition = screenHeight;
    }
}
