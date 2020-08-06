package com.percyvega.annotation.custom;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class CapitalizedPrinter implements Printer {
    @Override
    public void printNameInFavoriteLanguage() {
        String s = "Yo soy " + DefaultPrinter.class.getSimpleName();
        log.info(s.toUpperCase());
    }

    @InvokableWithASingleStringParameter(intro = "Capitalized")
    @Override
    public void printPrinter(String s) {
        Printer.super.printPrinter(s.toUpperCase());
    }
}
