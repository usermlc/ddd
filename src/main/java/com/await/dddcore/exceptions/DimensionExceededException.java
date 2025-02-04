package com.await.dddcore.exceptions;

public class DimensionExceededException extends RuntimeException {

    public DimensionExceededException(String message) {
        super(message);
    }
}