package com.percyvega.reflection.constructor;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
public class ConstructorTest {

    @Test
    void obtain_public_and_private_own_constructors() {
        Constructor<?>[] declaredConstructors = MyClass.class.getDeclaredConstructors();
        List<String> stringList = Arrays.stream(declaredConstructors)
                .map(Constructor::toString)
                .collect(Collectors.toList());

        assertThat(stringList).contains("private com.percyvega.reflection.constructor.MyClass()");
        assertThat(stringList).contains("public com.percyvega.reflection.constructor.MyClass(java.lang.String)");

        assertThat(stringList).hasSize(2);
    }

    @Test
    void obtain_public_own_constructors() {
        Constructor<?>[] constructors = MyClass.class.getConstructors();
        List<String> stringList = Arrays.stream(constructors)
                .map(Constructor::toString)
                .collect(Collectors.toList());

        assertThat(stringList).contains("public com.percyvega.reflection.constructor.MyClass(java.lang.String)");

        assertThat(stringList).hasSize(1);
    }

    @Test
    void change_private_constructor_to_public_constructor() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<?> declaredConstructor = MyClass.class.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        MyClass myClass = (MyClass) declaredConstructor.newInstance();

        assertThat(myClass).isInstanceOf(MyClass.class);
        assertThat(myClass.getClass().getName()).isEqualTo("com.percyvega.reflection.constructor.MyClass");
    }

}
