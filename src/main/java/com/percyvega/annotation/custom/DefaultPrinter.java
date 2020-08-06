package com.percyvega.annotation.custom;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class DefaultPrinter implements Printer {
    @Override
    public void printNameInFavoriteLanguage() {
        log.info("I am " + DefaultPrinter.class.getSimpleName());
    }
}
