package br.spring_security.controller;

import br.spring_security.config.WebSecurityConfig;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    private final WebSecurityConfig config;

    public WelcomeController(WebSecurityConfig config) {
        this.config = config;
    }

    @GetMapping
    public String welcome() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated()) {
            return "Acesso não autorizado";
        }

        return auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ADMIN")) ?
                "Bem vindo ao Spring Security, administrador!" :
                "Bem vindo ao Spring Security, usuário!";
    }


    @GetMapping("/users")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String users (){
        return "Acesso permitido";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String admin (){
        return "Acesso permitido admin";
    }

}
