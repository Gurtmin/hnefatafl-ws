package com.example.hnefatafl.exception;

public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(String objectId) {
        super("Object not found [id: " + objectId + "]");
    }
}
