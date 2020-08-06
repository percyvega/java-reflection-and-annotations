package com.percyvega.annotation.custom;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class HtmlPrinter implements Printer {
    @Override
    public void printNameInFavoriteLanguage() {
        String s = "Je suis " + DefaultPrinter.class.getSimpleName();
        log.info(toHtml(s));
    }

    private String toHtml(String s) {
        return "<html>" + s + "</html>";
    }

    void printHtmlVersion() {
        log.info("Version 5");
    }

    @InvokableWithASingleStringParameter(intro = "HTML")
    @Override
    public void printPrinter(String s) {
        Printer.super.printPrinter(toHtml(s));
    }
}
