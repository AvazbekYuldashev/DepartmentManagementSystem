package com.example.department_management_system.exp;

public class ItemNotFoundExeption extends RuntimeException {
    public ItemNotFoundExeption(String message) {
        super(message);
    }
}
