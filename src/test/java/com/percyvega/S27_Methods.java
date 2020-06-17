package com.percyvega;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
public class S27_Methods {

    @Test
    void obtain_public_and_private_direct_class_methods() {
        Method[] declaredMethods = MyPet.class.getDeclaredMethods();
        List<String> stringList = Arrays.stream(declaredMethods)
                .map(Method::toString)
                .collect(Collectors.toList());

        assertThat(stringList.stream().anyMatch(s -> s.equals("static int com.percyvega.S27_Methods$MyPet.access$000(com.percyvega.S27_Methods$MyPet)"))).isTrue();
        assertThat(stringList.stream().anyMatch(s -> s.equals("public void com.percyvega.S27_Methods$MyPet.setPetId(int)"))).isTrue();
        assertThat(stringList.stream().anyMatch(s -> s.equals("public int com.percyvega.S27_Methods$MyPet.getMaxPetEyesCount()"))).isTrue();
        assertThat(stringList.stream().anyMatch(s -> s.equals("public static java.lang.String com.percyvega.S27_Methods$MyPet.getFavoritePet()"))).isTrue();
        assertThat(stringList.stream().anyMatch(s -> s.equals("private int com.percyvega.S27_Methods$MyPet.getPetId()"))).isTrue();

        assertThat(stringList).hasSize(5);
    }

    @Test
    void obtain_public_direct_and_inherited_class_methods() {
        Method[] methods = MyPet.class.getMethods();
        List<String> stringList = Arrays.stream(methods)
                .map(Method::toString)
                .collect(Collectors.toList());

        assertThat(stringList.stream().anyMatch(s -> s.contains("getPetId"))).isFalse();

        assertThat(stringList.stream().anyMatch(s -> s.contains("setPetId"))).isTrue();
        assertThat(stringList.stream().anyMatch(s -> s.contains("getFavoritePet"))).isTrue();
        assertThat(stringList.stream().anyMatch(s -> s.contains("getMaxPetEyesCount"))).isTrue();
        assertThat(stringList.stream().anyMatch(s -> s.contains("getFavoriteAnimal"))).isTrue();
        assertThat(stringList.stream().anyMatch(s -> s.contains("getMaxAnimalEyesCount"))).isTrue();
        assertThat(stringList.stream().anyMatch(s -> s.contains("toString"))).isTrue();
        assertThat(stringList.stream().anyMatch(s -> s.contains("hashCode"))).isTrue();

        assertThat(stringList).hasSize(14);
    }

    @Test
    void get_method() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        MyPet myPet = new MyPet();

        Method setPetId = MyPet.class.getMethod("setPetId", int.class);
        assertThat(myPet.getPetId()).isBetween(0, 999);
        setPetId.invoke(myPet, 2000);
        assertThat(myPet.getPetId()).isEqualTo(2000);

        Method getAnimalId = MyAnimal.class.getDeclaredMethod("getAnimalId");
        getAnimalId.setAccessible(true);
        int animalId = (int) getAnimalId.invoke(myPet);
        assertThat(animalId).isBetween(0, 9);
    }

    static class MyPet extends MyAnimal {
        private int petId = new Random().nextInt(1000);

        public void setPetId(int petId) {
            this.petId = petId;
        }

        private int getPetId() {
            return petId;
        }

        public static String getFavoritePet() {
            return "Dog";
        }

        public int getMaxPetEyesCount() {
            return 8;
        }
    }

    static class MyAnimal {
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
}