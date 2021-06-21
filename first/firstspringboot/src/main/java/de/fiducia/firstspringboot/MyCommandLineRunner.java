package de.fiducia.firstspringboot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyCommandLineRunner implements CommandLineRunner {


    private final String message;

    MyCommandLineRunner(@Value("${MyCommandLineRunner.Gruss}") String message) {
        this.message = message;
        System.out.println("CTOR: " + message);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(message);
    }
}
