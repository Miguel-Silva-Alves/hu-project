package br.ifsp.husaocarlos.domain.usecases.utils.exceptions;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(String message) {
        super(message);
    }
}
