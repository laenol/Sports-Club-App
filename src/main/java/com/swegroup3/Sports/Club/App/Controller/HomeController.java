package com.swegroup3.Sports.Club.App.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping("/dashboard")
    public String home( ){
        return "index";
    }
}
