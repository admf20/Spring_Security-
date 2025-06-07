package com.example.SpringSecurity_Basico.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/admin")
    public String admin(){
        return "Hola Admin";
    }

    @GetMapping("/user")
    public String user(){
        return "Hola Usuario";
    }

    @GetMapping("/")
    public String home(){
        return "Pagina principal que requiere iniciar seccion";
    }
}
