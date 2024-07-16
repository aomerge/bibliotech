package com.aomerge.Bbliotech.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class usersController {
    @RequestMapping("/hello")
    public String sayHello() {
        return "Hello, World!";
    }
}
