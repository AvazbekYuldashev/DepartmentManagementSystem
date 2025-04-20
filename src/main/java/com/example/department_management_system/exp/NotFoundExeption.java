package com.example.department_management_system.exp;

public class NotFoundExeption extends RuntimeException {
  public NotFoundExeption(String message) {
    super(message);
  }
}
