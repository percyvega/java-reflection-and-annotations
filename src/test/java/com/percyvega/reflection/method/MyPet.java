package com.percyvega.reflection.method;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter
public class MyPet extends MyAnimal {
    private int petId = new Random().nextInt(1000);

    public static String getFavoritePet() {
        return "Dog";
    }

    public int getMaxPetEyesCount() {
        return 8;
    }
}
