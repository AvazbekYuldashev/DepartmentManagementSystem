package com.example.department_management_system.exp;

public class AppBadRequestExeption extends RuntimeException {
    public AppBadRequestExeption(String message) {
        super(message);
    }
}
