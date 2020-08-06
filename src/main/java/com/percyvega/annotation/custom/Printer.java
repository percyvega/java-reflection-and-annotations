package com.percyvega.annotation.custom;

public interface Printer {
    org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(Printer.class);

    void printNameInFavoriteLanguage();

    @InvokableWithASingleStringParameter
    default void printPrinter(String s) {
        log.info(s);
    }
}
