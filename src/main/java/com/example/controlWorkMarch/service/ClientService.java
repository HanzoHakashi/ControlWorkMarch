package com.example.controlWorkMarch.service;

import com.example.controlWorkMarch.dao.ClientDao;
import com.example.controlWorkMarch.entity.Client;
import com.example.controlWorkMarch.exeption.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {
    private final ClientDao clientDao;

    public ClientService(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public void registration(String name, String email,String password){
        Client client = new Client();
        client.setName(name);
        client.setEmail(email);
        client.setPassword(password);
        clientDao.save(client);
    }

    public Long findClientByEmail(String email) {
        Optional<Client> userOptional = clientDao.findClientByEmail(email);
        var client = userOptional
                .orElseThrow(()-> new ResourceNotFoundException("Не было найдено такого клиента"));
        if (client == null) {
            throw new RuntimeException("Client not found for email: " + email);
        }
        return client.getId();
    }
}
