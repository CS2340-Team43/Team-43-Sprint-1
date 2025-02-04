package com.example.demo_2340;

import static org.junit.Assert.assertTrue;

import com.example.demo_2340.Enemies_Implementation.Heavy1;
import com.example.demo_2340.Enemies_Implementation.Heavy2;
import com.example.demo_2340.Enemies_Implementation.Heavy3;
import com.example.demo_2340.Enemies_Implementation.Sprite;

import org.junit.Test;

/**
 * Test class Cases for Collision
 */
public class CollisionTest {

    /**
     * Tests to see what happens when
     * collision occurs with sprite
     *
     */
    @Test
    public void testSpriteCollision() {
        Sprite s1 = new Sprite();
        Player p1;
        p1 = Player.getInstance();
        s1.setxPosition(s1.move());
        s1.setyPosition(s1.move());
        p1.setxPosition((int)s1.getxPosition());
        p1.setyPosition((int)s1.getyPosition());
        s1.setxPosition((double)p1.getxPosition());
        s1.setyPosition((double)p1.getyPosition());

        assertTrue(p1.getxPosition() == (int)s1.getxPosition() && p1.getyPosition() ==
                s1.getyPosition());
    }

    /**
     * Test Case for Heavy1 random movement
     * Tests to see the heavy move randomly
     */
    @Test
    public void testHeavy1RandomMovement() {
        Heavy1 s1 = new Heavy1();
        Player p1;
        p1 = Player.getInstance();
        s1.setxPosition(s1.move());
        s1.setyPosition(s1.move());
        p1.setxPosition((int)s1.getxPosition());
        p1.setyPosition((int)s1.getyPosition());
        s1.setxPosition((double)p1.getxPosition());
        s1.setyPosition((double)p1.getyPosition());

        assertTrue(p1.getxPosition() == (int)s1.getxPosition() && p1.getyPosition() ==
                s1.getyPosition());
    }

    /**
     * Tests to see heavy2Random movement works
     */
    @Test
    public void testHeavy2RandomMovement() {
        Heavy2 s1 = new Heavy2();
        Player p1;
        p1 = Player.getInstance();
        s1.setxPosition(s1.move());
        s1.setyPosition(s1.move());
        p1.setxPosition((int)s1.getxPosition());
        p1.setyPosition((int)s1.getyPosition());
        s1.setxPosition((double)p1.getxPosition());
        s1.setyPosition((double)p1.getyPosition());

        assertTrue(p1.getxPosition() == (int)s1.getxPosition() && p1.getyPosition() ==
                s1.getyPosition());
    }

    /**
     * Test to see if heavy3 moves randomly
     */
    @Test
    public void testHeavy3RandomMovement() {
        Heavy3 s1 = new Heavy3();
        Player p1;
        p1 = Player.getInstance();
        s1.setxPosition(s1.move());
        s1.setyPosition(s1.move());
        p1.setxPosition((int)s1.getxPosition());
        p1.setyPosition((int)s1.getyPosition());
        s1.setxPosition((double)p1.getxPosition());
        s1.setyPosition((double)p1.getyPosition());

        assertTrue(p1.getxPosition() == (int)s1.getxPosition() && p1.getyPosition() ==
                s1.getyPosition());
    }


}
