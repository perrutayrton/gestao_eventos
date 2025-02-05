package com.perrut.gestao_eventos.exceptions;

public class FinalDateLessThanInitialDateException extends RuntimeException {

    public FinalDateLessThanInitialDateException() {
        super("Final date less than initial date");
    }
}
