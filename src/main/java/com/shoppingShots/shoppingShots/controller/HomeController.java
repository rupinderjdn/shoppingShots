package com.shoppingShots.shoppingShots.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// for receiving requests
@Controller
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @GetMapping("/")
    public String index()
    {
        logger.info("Here");
        return "../static/index.html";
    }

    @GetMapping("/login")
    public String login()
    {
        return "login";
    }
    @GetMapping("/register")
    public String register()
    {
        return "register";
    }
    @GetMapping("/products")
    public String products()
    {
        return "product";
    }

}
