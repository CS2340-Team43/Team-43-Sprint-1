package com.example.demo_2340;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.demo_2340.Enemies_Implementation.Enemies;
import com.example.demo_2340.Enemies_Implementation.EnemiesFactory;

import java.util.ArrayList;

public class EnemyInstantiationTest {
//    @Rule
//    public ActivityScenarioRule<GameScreen1> activityScenarioRule =
//            new ActivityScenarioRule<>(GameScreen1.class);
    @Test
    public void howManyEnemiesGameScreen1() {
        ArrayList<Enemies> enemies = new ArrayList<>();
        enemies.add(EnemiesFactory.buildEnemies("Sprite"));
        enemies.add(EnemiesFactory.buildEnemies("Heavy"));

        assertTrue(enemies.size() == 2);
    }

    @Test
    public void enemiesGameScreen1() {
        Enemies spriteTest = EnemiesFactory.buildEnemies("Sprite");
        Enemies heavyTest = EnemiesFactory.buildEnemies("Heavy");

        double xPos = spriteTest.getxPosition();
        double yPos = spriteTest.getyPosition();

        // checks that the sprite has coordinates at (0,0)
        assertTrue(xPos == 0);
        assertTrue(yPos == 0);

        xPos = heavyTest.getxPosition();
        yPos = heavyTest.getyPosition();

        // checks that the heavy enemy has coordinates at (0,0)
        assertTrue(xPos == 0);
        assertTrue(yPos == 0);
    }

    @Test
    public void howManyEnemiesGameScreen2() {
        ArrayList<Enemies> enemies = new ArrayList<>();
        enemies.add(EnemiesFactory.buildEnemies("Sprite"));
        enemies.add(EnemiesFactory.buildEnemies("Heavy2"));

        assertTrue(enemies.size() == 2);
    }

    @Test
    public void enemiesGameScreen2() {
        Enemies spriteTest = EnemiesFactory.buildEnemies("Sprite");
        Enemies heavyTest = EnemiesFactory.buildEnemies("Heavy2");

        double xPos = spriteTest.getxPosition();
        double yPos = spriteTest.getyPosition();

        // checks that the sprite has coordinates at (0,0)
        assertTrue(xPos == 0);
        assertTrue(yPos == 0);

        xPos = heavyTest.getxPosition();
        yPos = heavyTest.getyPosition();

        // checks that the heavy enemy has coordinates at (0,0)
        assertTrue(xPos == 0);
        assertTrue(yPos == 0);
    }

    @Test
    public void howManyEnemiesGameScreen3() {
        ArrayList<Enemies> enemies = new ArrayList<>();
        enemies.add(EnemiesFactory.buildEnemies("Sprite"));
        enemies.add(EnemiesFactory.buildEnemies("Heavy"));

        assertTrue(enemies.size() == 2);
    }

    @Test
    public void enemiesGameScreen3() {
        Enemies spriteTest = EnemiesFactory.buildEnemies("Sprite");
        Enemies heavyTest = EnemiesFactory.buildEnemies("Heavy");

        double xPos = spriteTest.getxPosition();
        double yPos = spriteTest.getyPosition();

        // checks that the sprite has coordinates at (0,0)
        assertTrue(xPos == 0);
        assertTrue(yPos == 0);

        xPos = heavyTest.getxPosition();
        yPos = heavyTest.getyPosition();

        // checks that the heavy enemy has coordinates at (0,0)
        assertTrue(xPos == 0);
        assertTrue(yPos == 0);
    }

}
