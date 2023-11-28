package com.example.demo_2340.DecoratorPowerUp;

import com.example.demo_2340.Player;

/**
 * SpeedPowerUp class
 * extends Decorator class
 * implements double speed effect on player
 */
public class SpeedPowerUp extends PowerUpDecorator {
    public SpeedPowerUp(PowerUp healthPower) {
        super(healthPower);
    }

    @Override
    public void powerUpHero(Player hero) {
        super.powerUpHero(hero);
        speedPowerMethod(hero);
    }
    private void speedPowerMethod(Player hero) {
        hero.setSpeed(2);
    }
}
