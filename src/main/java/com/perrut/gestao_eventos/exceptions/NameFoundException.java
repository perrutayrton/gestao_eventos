package com.perrut.gestao_eventos.exceptions;

public class NameFoundException extends RuntimeException {

    public NameFoundException() {
        super("company already registred");
    }
}
