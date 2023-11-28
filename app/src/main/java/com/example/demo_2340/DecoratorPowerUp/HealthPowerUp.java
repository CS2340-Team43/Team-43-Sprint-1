// HealthPowerUp.java
package com.example.demo_2340.DecoratorPowerUp;

import com.example.demo_2340.Player;

public class HealthPowerUp extends PowerUpBase {
    @Override
    public void powerUpHero(Player hero) {
        super.powerUpHero(hero);
        hero.setHealth(hero.getHealth() + 50); // Adjust the health increase as needed
    }
}
