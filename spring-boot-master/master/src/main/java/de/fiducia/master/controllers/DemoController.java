package de.fiducia.master.controllers;

import de.fiducia.master.controllers.dtos.PersonDTO;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import java.util.UUID;

@RestController
@RequestMapping("/demo")
//@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
//@SessionScope // VORSICHT beim Loadbalancing (Skalierung zumindest schwierig)
@RequestScope
public class DemoController {

    private int value = 0;

    @GetMapping(path = "/gruss", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getGruss() {
        return "Hallo Rest Controller";
    }

    @GetMapping(path = "/uuid", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getUUID() {
        return UUID.randomUUID().toString();
    }


    @GetMapping(path = "/value", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getValue() {
        return "value = " + value ++;
    }



}
