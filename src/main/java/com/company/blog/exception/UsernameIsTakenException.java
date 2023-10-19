package com.company.blog.exception;

public class UsernameIsTakenException extends RuntimeException{
    private static final long serialVersionUID = 1;

    public UsernameIsTakenException(String message) {
        super(message);
    }
}
