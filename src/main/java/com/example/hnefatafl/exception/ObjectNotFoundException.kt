package com.example.hnefatafl.exception

class ObjectNotFoundException(objectId: String) : RuntimeException("Object not found [id: $objectId]")