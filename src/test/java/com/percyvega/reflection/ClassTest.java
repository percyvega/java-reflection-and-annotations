package com.percyvega.reflection;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
public class ClassTest {

    @Test
    void obtaining_a_class_reference() throws ClassNotFoundException {
        Class<?> aClass1 = Class.forName("com.percyvega.reflection.ClassTest$MyClass"); // use when dynamically linked (class not present during compilation)
        Class<?> aClass2 = MyClass.class;
        Class<?> aClass3 = new MyClass().getClass(); // use when you already have an instance of the class

        assertThat(aClass1).isSameAs(aClass2);
        assertThat(aClass2).isSameAs(aClass3);
    }

    @Test
    void get_super_class() {
        Class<?> aClassSuperclass = MyClass.class.getSuperclass();
        assertThat(aClassSuperclass.getName()).isEqualTo("java.lang.Thread");
    }

    @Test
    void get_interfaces() {
        Class<?>[] interfaces = MyClass.class.getInterfaces();
        assertThat(interfaces.length).isEqualTo(2);
        assertThat(interfaces[0].getName()).isEqualTo("java.lang.Runnable");
        assertThat(interfaces[1].getName()).isEqualTo("java.lang.Cloneable");
    }

    static class MyClass extends Thread implements Runnable, Cloneable {
    }

}