package com.example.hnefatafl.exception

class ObjectAlreadyExistsException(name: String) : RuntimeException("Object already exists [id: $name]")