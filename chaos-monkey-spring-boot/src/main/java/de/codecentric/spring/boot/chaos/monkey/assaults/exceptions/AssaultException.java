package de.codecentric.spring.boot.chaos.monkey.assaults.exceptions;

public class AssaultException extends RuntimeException {

    public AssaultException(String message) {
        super(message);
    }
}
