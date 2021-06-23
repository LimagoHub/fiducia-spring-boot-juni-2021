package de.fiducia.master.services;

public class PersonServiceExecption extends Exception {
    public PersonServiceExecption() {
    }

    public PersonServiceExecption(String message) {
        super(message);
    }

    public PersonServiceExecption(String message, Throwable cause) {
        super(message, cause);
    }

    public PersonServiceExecption(Throwable cause) {
        super(cause);
    }

    public PersonServiceExecption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
