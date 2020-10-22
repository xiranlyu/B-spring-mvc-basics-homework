package com.thoughtworks.capacity.gtb.mvc;

public class UsernameExistsException extends RuntimeException {
    public UsernameExistsException (String message) {
        super(message);
    }
}

