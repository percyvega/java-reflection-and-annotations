package com.percyvega.reflection.modifiers;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
public class ModifiersTest {

    @Test
    void class_modifiers() {
        final int modifiers = MyClass.class.getModifiers();

        assertThat(modifiers & Modifier.PUBLIC).isOne();

        assertThat(Modifier.isPublic(modifiers)).isTrue();

        assertThat(Modifier.toString(modifiers)).isEqualTo("public");
    }

    @Test
    void constructor_modifiers() throws NoSuchMethodException {
        Constructor<MyClass> constructor = MyClass.class.getDeclaredConstructor();

        int modifiers = constructor.getModifiers();

        assertThat(Modifier.isPublic(modifiers)).isTrue();
        assertThat(Modifier.isPrivate(modifiers)).isFalse();

        assertThat(Modifier.toString(modifiers)).isEqualTo("public");
    }

    @Test
    void method_modifiers() throws NoSuchMethodException {
        Method setId = MyClass.class.getDeclaredMethod("setId", long.class);

        int modifiers = setId.getModifiers();

        assertThat(Modifier.isPrivate(modifiers)).isFalse();
        assertThat(Modifier.isStatic(modifiers)).isTrue();

        assertThat(Modifier.toString(modifiers)).isEqualTo("public static");
    }

}