package com.example.controlWorkMarch.controller;

import com.example.controlWorkMarch.service.ClientService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registration")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/new")
    public void registration(String name, String email, String password){
        clientService.registration(name,email,password);
    }

}
