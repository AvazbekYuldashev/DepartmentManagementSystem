package com.example.department_management_system.config;

import com.example.department_management_system.entity.EmployeeEntity;
import com.example.department_management_system.repository.employee.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService{
    @Autowired
    private EmployeeRepository profileRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<EmployeeEntity> optional = profileRepository.findByEmailAndVisibleTrue(login);
        if (optional.isEmpty()){throw new UsernameNotFoundException(login);}
        EmployeeEntity profile = optional.get();
        CustomUserDetails userDetails = new CustomUserDetails(profile);
        return userDetails;
    }
}
