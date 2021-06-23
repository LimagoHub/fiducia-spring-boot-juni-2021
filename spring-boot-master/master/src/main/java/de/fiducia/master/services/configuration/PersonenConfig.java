package de.fiducia.master.services.configuration;

import de.fiducia.master.repositories.PersonenRepository;
import de.fiducia.master.services.PersonenService;
import de.fiducia.master.services.impl.PersonenServiceImpl;
import de.fiducia.master.services.models.PersonMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
public class PersonenConfig {

    @Bean
    @Qualifier("antipathen")
    @Primary
    //@Profile("production")
    public List<String> antipathen() {
        return List.of("Attila", "Peter", "Paul","Mary");
    }
    @Bean
    @Qualifier("fruits")
    //@Profile("test")
    public List<String> getFruits() {
        return List.of("Banana", "Apple", "Strawberry","Cherry");
    }

//    @Bean
//    public PersonenService personenService(PersonenRepository repo, PersonMapper mapper, @Qualifier("antipathen") List<String> antipathen) {
//        return new PersonenServiceImpl(repo, mapper, antipathen);
//    }
}
