package com.swegroup3.Sports.Club.App.security;

import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
public class SecuredPassword {
    private String encodePassword ;
    public SecuredPassword(String rawPassword){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        encodePassword = encoder.encode(rawPassword);
    }

}
