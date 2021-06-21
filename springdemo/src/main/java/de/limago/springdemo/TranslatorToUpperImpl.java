package de.limago.springdemo;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Primary
@Qualifier("upper")
public class TranslatorToUpperImpl implements Translator {
    @Override
    public String translate(String message) {
        return message.toUpperCase();
    }
}
