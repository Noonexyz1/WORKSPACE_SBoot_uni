package com.accounts.service;

import com.accounts.app.request.TransferenciaRequest;
import com.accounts.commons.enums.ErrMsg;
import com.accounts.commons.exceptions.BadRequesException;
import com.accounts.provider.ClientesProvider;
import com.accounts.provider.record.Cliente;
import com.accounts.repository.CuentaRepository;
import com.accounts.model.Cuenta;
import jakarta.transaction.Transactional;
import org.apache.catalina.valves.JsonAccessLogValve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Transactional
@Service
public class TransferenciaService {

    @Autowired
    ClientesProvider provider;
    @Autowired
    CuentaRepository repository;
    public String transferir(TransferenciaRequest request){
        Cliente origen = provider.detalle(request.origenID());
        Cliente destino = provider.detalle(request.origenID());
        Cuenta cuentaOrigen = repository.findByClienteId(origen.id());
        Cuenta cuentaDestino = repository.findByClienteId(destino.id());
        if(cuentaDestino == null){
            throw new BadRequesException(ErrMsg.CUENTA_NO_VALIDA.getCode(), ErrMsg.CUENTA_NO_VALIDA.getMessage());
        }
        if(cuentaOrigen == null ){
            throw new BadRequesException(ErrMsg.CUENTA_NO_VALIDA.getCode(), ErrMsg.CUENTA_NO_VALIDA.getMessage());
        }
        if(!request.moneda().equalsIgnoreCase(cuentaDestino.getMoneda().toString())){
            throw new BadRequesException(ErrMsg.MONEDA_INVALIDA.getCode(), ErrMsg.MONEDA_INVALIDA.getMessage());

        }
        if(cuentaOrigen.getSaldoDisponible().compareTo(request.monto()) > 0){
            cuentaOrigen.setSaldoDisponible(cuentaOrigen.getSaldo().subtract(request.monto()));
            cuentaDestino = repository.save(cuentaDestino); //se compromete
            transfer(request.monto(), cuentaOrigen, cuentaDestino);
            return "TRANSEFERENCIA realizada con exito";
        }

        throw new BadRequesException(ErrMsg.SALDO_INSUFICIENTE.getCode(), ErrMsg.SALDO_INSUFICIENTE.getMessage());

    }


    private void transfer(BigDecimal monto, Cuenta cuentaOrigen, Cuenta cuentaDestino){
        cuentaOrigen.setSaldo(cuentaOrigen.getSaldoDisponible());
        repository.save(cuentaOrigen);
        cuentaDestino.setSaldo(cuentaDestino.getSaldo().add(monto));
        cuentaDestino.setSaldoDisponible(cuentaDestino.getSaldo());
        repository.save(cuentaDestino);
    }
}
