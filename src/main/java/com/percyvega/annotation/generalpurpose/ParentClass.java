package com.percyvega.annotation.generalpurpose;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SuppressWarnings("all")
public class ParentClass {

    //    @SuppressWarnings("unused")
    public void method1() {
        log.info("Parent method1() running");
    }

    @Deprecated
    public void method2(int a) {
        log.info("Parent method2(int a) running. a = " + a);
    }

}
