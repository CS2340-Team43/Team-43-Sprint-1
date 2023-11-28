package com.example.demo_2340.Enemies_Implementation;
//enemies factory makes the enemy implementation factory method pattern
public class EnemiesFactory {
    //ennumerated the types of enemies
    public static Enemies buildEnemies(String type) {
        String sprite = "Sprite";
        String heavy = "Heavy";
        String heavy2 = "Heavy2";
        String heavy3 = "Heavy3";
        if (type == null || type.isEmpty()) {
            return null;
        } else {
            //factory method to create different types of enemies
            if (type.equals(sprite)) {
                return new Sprite();
            } else if (type.equals(heavy)) {
                return new Heavy1();
            } else if (type.equals(heavy2)) {
                return new Heavy2();
            } else if (type.equals(heavy3)) {
                return new Heavy3();
            }
            throw new IllegalArgumentException("Unknown Enemy " + type);
        }
    }
}
