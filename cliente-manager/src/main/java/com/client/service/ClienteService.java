package com.client.service;

import com.client.app.commons.constantes.ErrMsg;
import com.client.app.commons.exceptions.BadRequesException;
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
        if(repository.existsByNombres(cliente.getNombres())){
            //throw new BadRequesException(ErrMsg.NAME_IS_NOT_VALID.getCode(), ErrMsg.NAME_IS_NOT_VALID.getMessage());

            //Para probar el GenericERROR al momento de crear un objeto Usuario con el mismo nombre
            //double a = 5 / 0;

            throw new BadRequesException();

        }
        return repository.save(cliente);
    }
    public List<Cliente> recuperaTodo(){
        return repository.findAll();
    }

    public Cliente detalle(Long id){
        return repository.findById(id).orElseThrow(() -> new BadRequesException(ErrMsg.BAT_REQUEST_CLIENT.getCode(), ErrMsg.BAT_REQUEST_CLIENT.getMessage()));
    }


}
