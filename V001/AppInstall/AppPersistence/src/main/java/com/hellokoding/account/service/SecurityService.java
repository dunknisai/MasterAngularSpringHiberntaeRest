package com.hellokoding.account.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
    
    UserDetails autologinDetails(String username, String password);
}
