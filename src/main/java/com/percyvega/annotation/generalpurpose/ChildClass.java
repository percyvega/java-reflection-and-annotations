package com.percyvega.annotation.generalpurpose;

import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;

@Log4j2
public class ChildClass extends ParentClass {

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
