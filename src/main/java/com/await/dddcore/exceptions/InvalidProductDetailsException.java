package com.await.dddcore.exceptions;

public class InvalidProductDetailsException extends RuntimeException {

    public InvalidProductDetailsException(String message) {
        super(message);
    }
}
