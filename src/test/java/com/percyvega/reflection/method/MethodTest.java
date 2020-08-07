package com.percyvega.reflection.method;

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
public class MethodTest {

    @Test
    void obtain_public_own_and_inherited_methods() {
        Method[] methods = MyPet.class.getMethods();

        List<String> stringList = Arrays.stream(methods)
                .map(Method::toString)
                .collect(Collectors.toList());

        assertThat(stringList).hasSize(15);

        // not found because it is not public
        assertThat(stringList).doesNotContain("public void com.percyvega.reflection.method.MyPet.getPetId()");
        assertThat(stringList).doesNotContain("public void com.percyvega.reflection.method.MyPet.getPetId()");

        assertThat(stringList).contains("public int com.percyvega.reflection.method.MyPet.getMaxPetEyesCount()");
        assertThat(stringList).contains("public static java.lang.String com.percyvega.reflection.method.MyPet.getFavoritePet()");
        assertThat(stringList).contains("public int com.percyvega.reflection.method.MyAnimal.getMaxAnimalEyesCount()");
        assertThat(stringList).contains("public static java.lang.String com.percyvega.reflection.method.MyAnimal.getFavoriteAnimal()");
        assertThat(stringList).contains("public final native void java.lang.Object.wait(long) throws java.lang.InterruptedException");
        assertThat(stringList).contains("public final void java.lang.Object.wait(long,int) throws java.lang.InterruptedException");
        assertThat(stringList).contains("public final void java.lang.Object.wait() throws java.lang.InterruptedException");
        assertThat(stringList).contains("public boolean java.lang.Object.equals(java.lang.Object)");
        assertThat(stringList).contains("public java.lang.String java.lang.Object.toString()");
        assertThat(stringList).contains("public native int java.lang.Object.hashCode()");
        assertThat(stringList).contains("public final native java.lang.Class java.lang.Object.getClass()");
        assertThat(stringList).contains("public final native void java.lang.Object.notify()");
        assertThat(stringList).contains("public final native void java.lang.Object.notifyAll()");
    }

    @Test
    void obtain_public_and_private_own_methods() {
        Method[] declaredMethods = MyPet.class.getDeclaredMethods();

        List<String> stringList = Arrays.stream(declaredMethods)
                .map(Method::toString)
                .collect(Collectors.toList());

        assertThat(stringList).contains("public com.percyvega.reflection.method.MyPet com.percyvega.reflection.method.MyPet.setPetId(int)");
        assertThat(stringList).contains("public int com.percyvega.reflection.method.MyPet.getPetId()");
        assertThat(stringList).contains("public static java.lang.String com.percyvega.reflection.method.MyPet.getFavoritePet()");
        assertThat(stringList).contains("public int com.percyvega.reflection.method.MyPet.getMaxPetEyesCount()");

        assertThat(stringList).hasSize(4);
    }

    @Test
    void get_method() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        MyPet myPetInstance = new MyPet();

        Method setPetId = MyPet.class.getMethod("setPetId", int.class);
        assertThat(myPetInstance.getPetId()).isBetween(0, 999);
        setPetId.invoke(myPetInstance, 2000);
        assertThat(myPetInstance.getPetId()).isEqualTo(2000);

        Method getAnimalId = MyAnimal.class.getDeclaredMethod("getAnimalId");
        getAnimalId.setAccessible(true);
        int animalId = (int) getAnimalId.invoke(myPetInstance);
        assertThat(animalId).isBetween(0, 9);
    }

}