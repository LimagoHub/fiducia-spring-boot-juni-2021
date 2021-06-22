package de.fiducia.master.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice // Aspekt
@Slf4j
public class MyExceptionHandler  extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x->x.getDefaultMessage()).collect(Collectors.toList());
        body.put("error", errors);
        body.put("gruss","Hallo");
        log.error("Upps", ex);// !!!!!! W I C H T I G
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleDozentHatdochAufgepasst(Exception ex, WebRequest webRequest) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("handlerType", "Generic Exceptionhandler");
        body.put("message", "# >" + ex.getMessage());
        body.put("ExceptionType", "# >" + ex.getClass().getName());
        log.error("Upps", ex);// !!!!!! W I C H T I G
        return ResponseEntity.badRequest().body(body);
    }
    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseEntity<Object> handleDozentHatNichtAufgepasst(ArrayIndexOutOfBoundsException ex, WebRequest webRequest) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("handlerType", "Special Exceptionhandler");
        body.put("message", "# >" + ex.getMessage());
        body.put("ExceptionType", "# >" + ex.getClass().getName());
        log.error("Upps", ex);// !!!!!! W I C H T I G
        return ResponseEntity.badRequest().body(body);
    }

}
