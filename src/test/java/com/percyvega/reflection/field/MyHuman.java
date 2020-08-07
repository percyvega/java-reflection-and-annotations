package com.percyvega.reflection.field;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyHuman extends MyLivingBeingClass {
    private int age;
    public String lastname;

    public MyHuman() {
        setSpecies("human");
    }

    public MyHuman(int age, String lastname) {
        this();
        this.age = age;
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return "MyHumanClass{" +
                "age=" + age +
                ", lastname='" + lastname + '\'' +
                ", id=" + getId() +
                ", species='" + getSpecies() + '\'' +
                '}';
    }
}
