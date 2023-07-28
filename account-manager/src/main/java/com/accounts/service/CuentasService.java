package com.accounts.service;

import com.accounts.app.request.CuentaRequest;
import com.accounts.app.response.CuentaResponse;
import com.accounts.commons.enums.Moneda;
import com.accounts.model.Cuenta;
import com.accounts.provider.ClientesProvider;
import com.accounts.provider.record.Cliente;
import com.accounts.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service                                                //todos los Services son archivos Singleton
public class CuentasService {
     @Autowired
     CuentaRepository repository;
     @Autowired
     ClientesProvider provider;

     public CuentaResponse crear(CuentaRequest request){
          //para crear la Cuenta, primero necesitamos el Cliente que se nos es enviado por json de otro microservicio
          //recuperamos el Cliente del PROVIDER CLIENTE interface que inyectamos arriba
          Cliente cliente = provider.detalle(request.clienteId());
          Cuenta cuenta = repository.save(Cuenta.builder()
                  .moneda(Moneda.valueOf(request.moneda()))
                  .numero(UUID.randomUUID().toString())
                  .saldo(BigDecimal.ZERO)
                  .saldoDisponible(BigDecimal.ZERO)
                  .habilitado(true)
                  .clienteId(cliente.id())
                  .build());

          return CuentaResponse.builder()
                  .numero(cuenta.getNumero())
                  .moneda(cuenta.getMoneda())
                  .saldoDisponible(cuenta.getSaldoDisponible())
                  .habilitado(cuenta.isHabilitado()? "SI":"NO")
                  .build();
     }



}
