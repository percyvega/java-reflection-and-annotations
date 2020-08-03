package com.percyvega.annotation;

import lombok.extern.log4j.Log4j2;

import java.lang.annotation.*;

@Log4j2
public class MetaAnnotationsExample {

    public static void main(String[] args) {
        Person person = new Person();

        log.info(person);
    }

}

@Documented
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.CLASS)
@interface MyUniqueValue {

    int value() default 0;

}

class Person {

    @MyUniqueValue
    int id;
    int name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}