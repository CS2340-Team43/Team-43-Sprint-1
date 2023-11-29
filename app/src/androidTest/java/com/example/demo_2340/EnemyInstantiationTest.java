package com.example.demo_2340;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

public class EnemyInstantiationTest {
    public ActivityScenarioRule<GameScreen1> activityScenarioRule =
            new ActivityScenarioRule<>(GameScreen1.class);
    public void enemiesGameScreen1() {
        GameScreen1 gameScreen1 = new GameScreen1();
        gameScreen1.sprite

    }

}
