package com.percyvega.reflection;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
public class ClassTest {

    @Test
    void waysOfObtainingAClassReference() throws ClassNotFoundException {
        Class<?> aClass1 = Class.forName(MyClass.class.getName());
        Class<?> aClass2 = MyClass.class;
        Class<?> aClass3 = new MyClass().getClass();

        assertThat(aClass1).isSameAs(aClass2);
        assertThat(aClass2).isSameAs(aClass3);
    }

    @Test
    void obtainSuperclassAndInterfaces() {
        Class<?> aClassSuperclass = MyClass.class.getSuperclass();
        assertThat(aClassSuperclass.getName()).isEqualTo("java.lang.Thread");

        Class<?>[] interfaces = MyClass.class.getInterfaces();
        assertThat(interfaces.length).isEqualTo(2);
        assertThat(interfaces[0].getName()).isEqualTo("java.lang.Runnable");
        assertThat(interfaces[1].getName()).isEqualTo("java.lang.Cloneable");
    }

    class MyClass extends Thread implements Runnable, Cloneable {
    }

}