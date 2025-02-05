package com.perrut.gestao_eventos.exceptions;

public class NotExistsEventsInPeriodException extends  RuntimeException{

    public NotExistsEventsInPeriodException() {
        super("Not Exists events in period");
    }
}
