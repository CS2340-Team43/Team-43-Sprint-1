// SpeedPowerUp.java
package com.example.demo_2340.DecoratorPowerUp;

import android.util.Log;
import com.example.demo_2340.Player;

public class SpeedPowerUp extends PowerUpBase {
    private boolean isUsed = false;

    @Override
    public void powerUpHero(Player hero) {
        if (!isUsed) {
            super.powerUpHero(hero);
            double newSpeed = hero.getSpeed() + 0.5;

            // Ensure the speed does not go over 2
            hero.setSpeed(Math.min(newSpeed, 2.0));

            this.isUsed = true;
        }
    }
}
