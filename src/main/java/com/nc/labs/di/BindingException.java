package com.nc.labs.di;

/**
 * error to handling cases when the injection cannot be applied
 */
public class BindingException extends RuntimeException{
    public BindingException() {
    }

    public BindingException(String message) {
        super(message);
    }
}
