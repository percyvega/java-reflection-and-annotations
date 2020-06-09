package com.percyvega;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
public class S24_ClassObject {

    @Test
    void waysOfObtainingAClassReference() throws ClassNotFoundException {
        Class<?> aClass1 = Class.forName(MyClass.class.getName());
        Class<?> aClass2 = MyClass.class;
        Class<?> aClass3 = new MyClass().getClass();

        assertThat(aClass1).isSameAs(aClass2);
        assertThat(aClass2).isSameAs(aClass3);
    }

    @Test
    void obtainSuperclassAndInterfaces() throws ClassNotFoundException {
        Class<?> aClass = Class.forName(MyClass.class.getName());
        Class<?> aClassSuperclass = aClass.getSuperclass();
        Class<?>[] interfaces = aClass.getInterfaces();

        assertThat(aClassSuperclass.getSimpleName()).isEqualTo("Thread");
        assertThat(interfaces.length).isEqualTo(2);
    }

    class MyClass extends Thread implements Runnable, Cloneable {
    }

}