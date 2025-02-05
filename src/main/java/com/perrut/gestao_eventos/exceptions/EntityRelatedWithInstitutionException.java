package com.perrut.gestao_eventos.exceptions;

public class EntityRelatedWithInstitutionException extends RuntimeException {

    public EntityRelatedWithInstitutionException() {
        super("Exists events related with institution");
    }
}
