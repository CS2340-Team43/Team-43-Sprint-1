// SpeedPowerUp.java
package com.example.demo_2340.DecoratorPowerUp;

import com.example.demo_2340.Player;

public class SpeedPowerUp extends PowerUpBase {
    @Override
    public void powerUpHero(Player hero) {
        super.powerUpHero(hero);
        hero.setSpeed(hero.getSpeed() + 1); // Double the player speed
    }
}
