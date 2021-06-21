package de.fiducia.firstspringboot;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("development")
public class TranslatorToLowerImpl implements Translator {


    @Override
    public String translate(String message) {
        return message.toLowerCase();
    }
}
