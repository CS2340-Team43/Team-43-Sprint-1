package com.example.demo_2340;

import static org.junit.Assert.assertTrue;

import com.example.demo_2340.DecoratorPowerUp.HealthPowerUp;
import com.example.demo_2340.DecoratorPowerUp.PowerUp;
import com.example.demo_2340.DecoratorPowerUp.PowerUpBase;
import com.example.demo_2340.DecoratorPowerUp.SlowPowerUp;
import com.example.demo_2340.DecoratorPowerUp.SpeedPowerUp;
import com.example.demo_2340.Enemies_Implementation.Enemies;
import com.example.demo_2340.Enemies_Implementation.EnemiesFactory;

import org.junit.Test;

import java.util.ArrayList;

public class PowerUpEffects {
//    @Rule
//    public ActivityScenarioRule<GameScreen1> activityScenarioRule =
//            new ActivityScenarioRule<>(GameScreen1.class);
    @Test
    public void speedUpTest() {
        Player player = Player.getInstance();
        Player playerChanged = player.clonePlayer();
        PowerUpBase speedPowerUp = new SpeedPowerUp();
        speedPowerUp.powerUpHero(playerChanged);
        assertTrue(player.getSpeed() < playerChanged.getSpeed());
    }

    @Test
    public void slowDownTest() {
        Player player = Player.getInstance();
        Player playerChanged = player.clonePlayer();
        PowerUpBase slowPowerUp = new SlowPowerUp();
        slowPowerUp.powerUpHero(playerChanged);
        assertTrue(player.getSpeed() > playerChanged.getSpeed());
    }

    @Test
    public void increaseHealthTest() {
        Player player = Player.getInstance();
        Player playerChanged = player.clonePlayer();
        // establish changedPlayer having less health than player
        playerChanged.setHealth(player.getHealth() - 50);
        assertTrue(player.getHealth() > playerChanged.getHealth());
        // apply powerup
        PowerUpBase increaseHealth = new HealthPowerUp();
        increaseHealth.powerUpHero(playerChanged);
        // check that they're the same now because health increased
        assertTrue(player.getHealth() == playerChanged.getHealth());
    }

}
