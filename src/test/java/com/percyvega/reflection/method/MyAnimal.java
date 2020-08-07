package com.percyvega.reflection.method;

import java.util.Random;

public class MyAnimal {
    private int getAnimalId() {
        return new Random().nextInt(10);
    }

    public static String getFavoriteAnimal() {
        return "Lion";
    }

    public int getMaxAnimalEyesCount() {
        return 100;
    }
}

