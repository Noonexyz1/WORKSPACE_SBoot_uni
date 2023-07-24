package com.accounts.app.controller;

import com.accounts.app.request.CuentaRequest;
import com.accounts.app.response.CuentaResponse;
import com.accounts.service.CuentasService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 86400)
@RestController
@Validated
@RequestMapping(path = "/cuentas")
public class CuentasController {

    @Autowired
    CuentasService service;

    @PostMapping(path = "/", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CuentaResponse> crearCuenta(@Valid @RequestBody CuentaRequest request){
        return new ResponseEntity<>(service.crear(request), HttpStatus.CREATED);  //HttpStatus.OK que es 200 y asi los demas
    }

}
