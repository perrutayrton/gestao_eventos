package com.perrut.gestao_eventos.exceptions;

public class InstitutionNotFoundException extends RuntimeException {

    public InstitutionNotFoundException() {
        super("Institution not found");
    }
}
