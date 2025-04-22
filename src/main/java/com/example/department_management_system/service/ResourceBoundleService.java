package com.example.department_management_system.service;

import com.example.department_management_system.enums.AppLangulage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class ResourceBoundleService {
    @Autowired
    @Qualifier("messageSource")
    private MessageSource boundleMessage;

    public String getMessage(String code, AppLangulage lang) {
        return boundleMessage.getMessage(code, null, new Locale(lang.name()));
    }
}
