package ru.netolgy.hw_spring_boot_rest.controller;

import ru.netolgy.hw_spring_boot_rest.model.Authorities;
import ru.netolgy.hw_spring_boot_rest.service.AuthorizationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorizationController {
    private final AuthorizationService service;

    public AuthorizationController(AuthorizationService service) {
        this.service = service;
    }

    @GetMapping("/authorize")
    public List<Authorities> getAuthorities(@RequestParam("user") String user,
                                            @RequestParam("password") String password) {
        return service.getAuthorities(user, password);
    }
}