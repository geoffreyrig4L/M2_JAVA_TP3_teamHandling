package fr.sdv.cnit.university.api.service;

public class TeamInvalidException extends RuntimeException {

    public TeamInvalidException(String message) {
        super(message);
    }
}