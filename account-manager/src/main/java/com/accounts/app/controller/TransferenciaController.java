package com.accounts.app.controller;

import com.accounts.app.request.TransferenciaRequest;
import com.accounts.service.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 86400)
@RestController
@Validated
@RequestMapping(path = "/transferencias")
public class TransferenciaController {
    @Autowired
    private TransferenciaService service;

    @PostMapping(
            path = "/",
            produces = { MediaType.APPLICATION_JSON_VALUE },
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> transferir(@RequestBody TransferenciaRequest request){
        return new ResponseEntity<>(service.transferir(request), HttpStatus.CREATED);
    }  // continuar video 1.7 del la clase 5

}
