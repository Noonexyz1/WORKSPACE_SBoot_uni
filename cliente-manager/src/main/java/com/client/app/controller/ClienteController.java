package com.client.app.controller;

import com.client.model.Cliente;
import com.client.service.ClienteService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "www.peticionesDe.com", maxAge = )
@CrossOrigin(origins = "*", maxAge = 86400)
@RestController
@Validated
@RequestMapping(path = "/clientes")
public class ClienteController {
    @Autowired
    private ClienteService service;

    @GetMapping(path = {""}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Cliente>> get(){
        List<Cliente> clientes = service.recuperaTodo();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }
    @GetMapping(path = {"/{id}"}, produces = {MediaType.APPLICATION_JSON_VALUE})  //PathVariable, por el path recibimos un ID
    public ResponseEntity<Cliente> getDetalle(@PathVariable Long id){
        return new ResponseEntity<>(service.detalle(id), HttpStatus.OK);
    }
    @PostMapping(path = {"/"}, produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cliente> crearCliente(@Valid @RequestBody Cliente cliente){
        return new ResponseEntity<>(service.crear(cliente), HttpStatus.OK);
    }

    //Cuando se afecte a una sola tabla, se puede poner todos los metodos
    //segun las buenas practicas.

}
