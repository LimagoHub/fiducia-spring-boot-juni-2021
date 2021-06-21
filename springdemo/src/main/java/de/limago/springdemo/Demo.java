package de.limago.springdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


@Component // Stellt die Klasse unter die Verwaltung von Spring
@Scope(BeanDefinition.SCOPE_SINGLETON) // Definiert den Lebenszyklus
@Lazy(value = false) // Verzögertes Erzeugen (erst bei Bedarf)
public class Demo {


    private final Translator translator;

    @Autowired // loest Abhängikeiten automatisch auf. Beide Klassen müssen unter der Verwaltung von Spring stehen
    public Demo(@Qualifier("lower") /* Bei Typkonflikten schaltet er auf Autowire by name  */final Translator translator) {
        this.translator = translator;
        System.out.println("Ctor Demo");
    }
    @PostConstruct  // Nach dem Konstruktor und nach Setter und Fieldinject
    public void foo() {
        System.out.println(translator.translate("Hier ist demo"));
    }

    @PostConstruct
    public void peter() {
        System.out.println(translator.translate("Init AbCd"));
    }

    @PreDestroy // Vor zerstörung (nur bei Singleton)
    public void anna() {
        System.out.println(translator.translate("Fertig AbCd"));
    }
}
