// SlowPowerUp.java
package com.example.demo_2340.DecoratorPowerUp;

import com.example.demo_2340.Player;

public class SlowPowerUp extends PowerUpBase {
    private boolean isUsed = false;

    @Override
    public void powerUpHero(Player hero) {
        if (!isUsed) {
            super.powerUpHero(hero);
            double newSpeed = hero.getSpeed() - 0.5;

            // Ensure the speed is not below 0.5
            hero.setSpeed(Math.max(newSpeed, 0.5));

            this.isUsed = true;
        }
    }
}