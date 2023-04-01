package com.example.controlWorkMarch.service;

import com.example.controlWorkMarch.dao.ClientDao;
import com.example.controlWorkMarch.entity.Client;
import org.springframework.stereotype.Service;

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
}
