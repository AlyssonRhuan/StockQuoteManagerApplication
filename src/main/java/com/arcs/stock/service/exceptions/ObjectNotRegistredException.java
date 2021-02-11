package com.arcs.stock.service.exceptions;

public class ObjectNotRegistredException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ObjectNotRegistredException(String message) {
        super(message);
    }
}
