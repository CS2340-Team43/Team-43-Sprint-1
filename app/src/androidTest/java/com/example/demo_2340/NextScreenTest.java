package com.example.demo_2340;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

/**
 * Tests to see if next screen works
 */
public class NextScreenTest {
    @Rule
    public ActivityScenarioRule<GameScreen1> activityScenarioRule =
            new ActivityScenarioRule<>(GameScreen1.class);

    /**
     * Tests to see if score carries after changing screens
     */
    @Test
    public void scoreTest() {
        for (int i = 0; i < 53; i++){
            onView(withId(R.id.buttonUp)).perform(click());
        }
        onView(withId(R.id.buttonUp)).perform(click());
    }
}
