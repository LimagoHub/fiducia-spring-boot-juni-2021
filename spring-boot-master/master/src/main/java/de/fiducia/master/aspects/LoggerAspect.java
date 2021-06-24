package de.fiducia.master.aspects;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Aspect
@Component
@Slf4j
public class LoggerAspect {



    @Before("Pointcuts.personControllerMethods()")
    public void logAdvice(JoinPoint joinPoint) {
        System.out.println("############### "+ joinPoint.getSignature().getName()+ " ###################");
    }

    @AfterReturning(value = "Pointcuts.restControllerMethodes()", returning = "retValue")
    public void logAfterAdvice(JoinPoint joinPoint, Object retValue) {
        System.out.println(retValue);
        System.out.println("############### "+ joinPoint.getSignature().getName()+ " ###################");
    }

    @AfterThrowing(value = "execution(public * de.fiducia.master.services.impl.PersonenServiceImpl.*(..))", throwing = "ex")
    public void logAfterAdvice(JoinPoint joinPoint, Throwable ex) {
       log.error(ex.getMessage(), ex);
    }
    @Around(value = "execution(public * de.fiducia.master.services.impl.PersonenServiceImpl.*(..))")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable{
        Object result;
        try {
            Instant start = Instant.now();

            result =  joinPoint.proceed(); // WICHTIG

            Instant ende = Instant.now();
            Duration duration = Duration.between(start,ende);
            log.warn("Duration ist {}", duration.toMillis());

        } catch (Throwable throwable) {
           log.info("Bla") ;
           throw throwable;
        } finally {
            log.info("Bla") ;
        }
        return result;
    }
}
