package de.fiducia.master.aspects;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    @Pointcut(value = "execution(public * de.fiducia.master.controllers.PersonenController.*(..))")
    public void personControllerMethods(){}

    @Pointcut(value = "within(@org.springframework.web.bind.annotation.RestController *)" )
    public void restControllerMethodes() {}
}
