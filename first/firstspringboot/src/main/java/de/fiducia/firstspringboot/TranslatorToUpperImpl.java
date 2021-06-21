package de.fiducia.firstspringboot;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Primary
@Profile("production")
public class TranslatorToUpperImpl implements Translator {
    @Override
    public String translate(String message) {
        return message.toUpperCase();
    }
}
