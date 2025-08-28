package br.spring_security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping
    public String welcome() {
        return "Bem vindo ao Spring Security!";
    }

    @GetMapping("/users")
    @PreAuthorize("hasAnyRole('ADMIN', 'USERS')")
    public String users (){
        return "Acesso permitido";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String admin (){
        return "Acesso permitido";
    }

}
