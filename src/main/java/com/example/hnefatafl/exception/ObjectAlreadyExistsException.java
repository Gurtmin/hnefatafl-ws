package com.example.hnefatafl.exception;

public class ObjectAlreadyExistsException extends RuntimeException {
    public ObjectAlreadyExistsException(String name) {
        super("Object already exists [id: " + name + "]");
    }
}