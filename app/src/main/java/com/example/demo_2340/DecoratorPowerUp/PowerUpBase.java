package com.example.demo_2340.DecoratorPowerUp;

import com.example.demo_2340.Player;

/**
 * Base PowerUp without decoration
 */
public class PowerUpBase implements PowerUp{
    public void powerUpHero(Player hero) {
        hero.setHealth(100);
    }
}
