package com.percyvega;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
public class S26_Fields {

    MyHuman myHuman;

    @BeforeEach
    void beforeEach() {
        myHuman = new MyHuman(40, "Vega");
        assertThat(myHuman.toString()).isEqualTo("MyHumanClass{age=40, lastname='Vega', id=null, species='human'}");
    }

    @Test
    void obtainAllPublicFields() {
        Field[] fields = MyHuman.class.getFields();

        assertThat(fields).hasSize(2);
        assertThat(fields[0].getType()).isEqualTo(String.class);
        assertThat(fields[0].getName()).isEqualTo("lastname");
        assertThat(fields[1].getType()).isEqualTo(String.class);
        assertThat(fields[1].getName()).isEqualTo("species");

        assertThat(Arrays.stream(fields).map(Field::getName)).containsExactlyInAnyOrder("lastname", "species");
    }

    @Test
    void obtainAllFields() {
        Field[] declaredFields = MyHuman.class.getDeclaredFields();

        assertThat(declaredFields).hasSize(3);
        assertThat(Arrays.stream(declaredFields).map(Field::getName)).containsExactlyInAnyOrder("age", "lastname", "this$0");
    }

    @Test
    void changePublicField() throws NoSuchFieldException, IllegalAccessException {
        Field lastname = MyHuman.class.getField("lastname");
        lastname.set(myHuman, "Reyes");
        assertThat(myHuman.getLastname()).isEqualTo("Reyes");
    }

    @Test
    void changePrivateField() throws NoSuchFieldException, IllegalAccessException {
        Field age = MyHuman.class.getDeclaredField("age");
        try {
            age.set(myHuman, 1000);
        } catch (IllegalAccessException e) {
            age.setAccessible(true);
            age.set(myHuman, 23);
        }
        assertThat(myHuman.getAge()).isEqualTo(23);
    }

    class MyHuman extends MyLivingBeingClass {

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

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
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

    class MyLivingBeingClass {
        private Long id;
        public String species;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getSpecies() {
            return species;
        }

        public void setSpecies(String species) {
            this.species = species;
        }
    }

}