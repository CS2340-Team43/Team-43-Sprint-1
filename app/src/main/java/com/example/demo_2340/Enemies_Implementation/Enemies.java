package com.example.demo_2340.Enemies_Implementation;

//interface to implement factory method for enemies
public interface Enemies {
    //method to get type of enemy
    String getType();
    //move method to move the enemy
    double move();
    //getters and setters
    double getxPosition();

    void setxPosition(double xPosition);

    double getyPosition();

    void setyPosition(double yPosition);

    double getDamage();
    //more useful methods to outline for enemies

    void setInitialPosition(double x, double y);

    void onCollisionDetected(int i);

    void attack();
}
