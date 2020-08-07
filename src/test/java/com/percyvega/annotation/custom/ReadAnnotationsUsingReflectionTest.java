package com.percyvega.annotation.custom;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

@Log4j2
public class ReadAnnotationsUsingReflectionTest {

    List<Class<? extends Printer>> printerImplementations = Arrays.asList(DefaultPrinter.class, HtmlPrinter.class, CapitalizedPrinter.class);

    @Test
    void printPrinter() {
        new DefaultPrinter().printPrinter("Hola");
        new HtmlPrinter().printPrinter("Percy");
        new CapitalizedPrinter().printPrinter("Vega");
    }

    @Test
    void print_all_methods_and_all_their_annotations() {
        for (Class<? extends Printer> printerImplementation : printerImplementations) {
            log.info(printerImplementation.getSimpleName());
            Method[] methods = printerImplementation.getMethods();
            for (Method method : methods) {
                log.info("\t" + method.getName());
                Annotation[] annotations = method.getAnnotations();
                for (Annotation annotation : annotations) {
                    log.info("\t\t" + annotation);
                }
            }
        }
    }

    @Test
    void get_all_methods_with_a_specific_annotation_and_invoke_them() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        String singleStringParameterValue = "Percy";
        Class<InvokableWithASingleStringParameter> annotationToLookFor = InvokableWithASingleStringParameter.class;

        for (Class<? extends Printer> printerImplementation : printerImplementations) {
            Constructor<? extends Printer> printerImplementationConstructor = printerImplementation.getDeclaredConstructor();
            Printer printer = printerImplementationConstructor.newInstance();

            Method[] methods = printerImplementation.getMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(annotationToLookFor)) {
                    method.invoke(printer, singleStringParameterValue);
                }
            }
        }
    }

}

