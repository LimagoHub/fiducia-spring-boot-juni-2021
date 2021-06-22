package de.fiducia.master.controllers;

import de.fiducia.master.controllers.dtos.PersonDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping(path = "/gruss", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getGruss() {
        return "Hallo Rest";
    }



}
