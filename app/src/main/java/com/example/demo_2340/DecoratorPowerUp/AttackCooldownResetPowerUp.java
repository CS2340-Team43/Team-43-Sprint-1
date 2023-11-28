// AttackCooldownResetPowerUp.java
package com.example.demo_2340.DecoratorPowerUp;

import com.example.demo_2340.Player;

public class AttackCooldownResetPowerUp extends PowerUpBase {
    public AttackCooldownResetPowerUp(PowerUpBase powerUpBase) {

    }

    @Override
    public void powerUpHero(Player hero) {
        super.powerUpHero(hero);
        // Reset the attack cooldown
        hero.setAttackCooldown(false);
    }
}