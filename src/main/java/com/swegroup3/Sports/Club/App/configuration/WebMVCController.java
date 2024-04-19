package com.swegroup3.Sports.Club.App.configuration;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebMVCController implements WebMvcConfigurer {
    @Override
    public void addViewController(ViewControllerRegistry registry){
        registry.addViewController("/403").setViewName("403");
        registry.addViewController("/login").setViewName("login");

    }
}
