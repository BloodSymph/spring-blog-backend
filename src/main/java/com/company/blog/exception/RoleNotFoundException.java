package com.company.blog.exception;

public class RoleNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1;
    public RoleNotFoundException(String message) {
        super(message);
    }
}
