package com.percyvega.reflection.constructor;


import lombok.extern.log4j.Log4j2;

// This inner class has to be static because it's a field of the outer class
//      and we don't want to instantiate the outer class to use/access it.
@Log4j2
public class MyClass extends MySuperClass {
    private MyClass() {
        log.info(MyClass.class + " has been initialized!");
    }

    public MyClass(String s) {
        log.info(MyClass.class + " has been initialized with a String constructor parameter of " + s);
    }
}
