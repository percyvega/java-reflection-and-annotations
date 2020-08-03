package com.percyvega.annotation;

import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;

public class GeneralPurposeAnnotationsExample {
}

@Log4j2
@SuppressWarnings("all")
class Parent {

    //    @SuppressWarnings("unused")
    public void method1() {
        log.info("Parent method1() running");
    }

    @Deprecated
    public void method2(int a) {
        log.info("Parent method2(int a) running. a = " + a);
    }

}

@Log4j2
class ChildClass extends Parent {

    @Override
    public void method1() {
        log.info("ChildClass method1() running");
    }

    public static void main(String[] args) {

        @SuppressWarnings("unused")
        int a = 10;

        @SuppressWarnings({"rawtypes", "unused"})
        ArrayList arrayList = new ArrayList();

        ChildClass childClass = new ChildClass();
        childClass.method2(1);
    }
}
