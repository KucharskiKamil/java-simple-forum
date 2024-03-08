package com.example.Wersja2.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;
@Getter
@Setter
@Component
public class LocaleUtil {

    public Locale getLocale() {

        //Locale locale = LocaleContextHolder.getLocale(); //Automatyczne dobieranie
        //Locale locale = new Locale("en", "US"); //Sztywne ustawianie
        Locale locale = new Locale("pl", "PL"); //Sztywne ustawianie
        return locale;

    }
}

