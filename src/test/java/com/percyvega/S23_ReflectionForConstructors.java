package com.percyvega;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@Log4j2
public class S23_ReflectionForConstructors {

    @Test
    void change_private_constructor_to_public_constructor() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException {
        // usually this method of obtaining a reference to a class is used
        //  when the class type is unknown
        Class<?> aClass = Class.forName(MyClass.class.getName());
        Constructor<?> declaredConstructor = aClass.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        MyClass myClass = (MyClass) declaredConstructor.newInstance();
    }

    @Test
    void change_private_constructor_to_public_constructor2() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException {
        // use this method of obtaining a reference to a class when its type is known
        Constructor<MyClass> declaredConstructor = MyClass.class.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        MyClass myClass = declaredConstructor.newInstance();
    }

    private static class MyClass {
        public MyClass() {
            log.info(MyClass.class + " has been initialized!");
        }
    }

}
