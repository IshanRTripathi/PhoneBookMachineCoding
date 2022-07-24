package com.phonebook.phonebook.validation.exception;

public class ContactValidationError extends RuntimeException {
    public ContactValidationError(String s) {
        super(s);
    }
}
