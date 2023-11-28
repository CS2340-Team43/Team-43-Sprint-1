package com.example.demo_2340.DecoratorPowerUp;

import com.example.demo_2340.Player;

/**
 * Health PowerUpClass.
 * Decorates the base Power up to increase health by double
 */
public class HealthPowerUp extends PowerUpDecorator {
    public HealthPowerUp(PowerUp healthPower) {
        super(healthPower);
    }

    @Override
    public void powerUpHero(Player hero) {
        super.powerUpHero(hero);
        healthPowerMethod(hero);
    }

    private void healthPowerMethod(Player hero) {
        hero.setHealth(200);
    }
}
