package com.percyvega.reflection;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
public class FieldTest {

    MyHuman myHumanInstance;

    @BeforeEach
    void beforeEach() {
        myHumanInstance = new MyHuman(40, "Vega");
        assertThat(myHumanInstance.toString()).isEqualTo("MyHumanClass{age=40, lastname='Vega', id=null, species='human'}");
    }

    @Test
    void obtain_own_and_inherited_public_fields() {
        Field[] fields = MyHuman.class.getFields();

        assertThat(fields).hasSize(2);

        assertThat(fields[0].getType()).isEqualTo(String.class);
        assertThat(fields[0].getName()).isEqualTo("lastname");
        assertThat(fields[1].getType()).isEqualTo(String.class);
        assertThat(fields[1].getName()).isEqualTo("species");

        List<String> stringList = Arrays.stream(fields)
                .map(Field::getName)
                .collect(Collectors.toList());

        assertThat(stringList).contains("lastname");
        assertThat(stringList).contains("species");
    }

    @Test
    void obtain_public_and_private_own_fields() {
        Field[] declaredFields = MyHuman.class.getDeclaredFields();

        List<String> stringList = Arrays.stream(declaredFields)
                .map(Field::getName)
                .collect(Collectors.toList());

        assertThat(stringList).hasSize(3);

        assertThat(stringList).contains("age");
        assertThat(stringList).contains("lastname");
        assertThat(stringList).contains("this$0");
    }

    @Test
    void changePublicField() throws NoSuchFieldException, IllegalAccessException {
        Field lastname = MyHuman.class.getField("lastname");

        lastname.set(myHumanInstance, "Reyes");

        assertThat(myHumanInstance.getLastname()).isEqualTo("Reyes");
    }

    @Test
    void changePrivateField() throws NoSuchFieldException, IllegalAccessException {
        Field age = MyHuman.class.getDeclaredField("age");
        try {
            age.set(myHumanInstance, 1000);
        } catch (IllegalAccessException e) {
            age.setAccessible(true);
            age.set(myHumanInstance, 23);
        }
        assertThat(myHumanInstance.getAge()).isEqualTo(23);
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