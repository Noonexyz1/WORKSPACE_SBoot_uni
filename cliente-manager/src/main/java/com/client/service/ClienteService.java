package com.client.service;

import com.client.model.Cliente;
import com.client.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;


    public Cliente crear(Cliente cliente){
        return repository.save(cliente);
    }
    public List<Cliente> recuperaTodo(){
        return repository.findAll();
    }



}
