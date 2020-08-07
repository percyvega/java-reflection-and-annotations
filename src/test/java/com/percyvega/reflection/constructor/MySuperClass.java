package com.percyvega.reflection.constructor;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class MySuperClass {
    public MySuperClass() {
        log.info(MySuperClass.class + "has been initialized!");
    }

    public MySuperClass(int i) {
        log.info(MySuperClass.class + "has been initialized with a int constructor parameter of " + i);
    }
}
