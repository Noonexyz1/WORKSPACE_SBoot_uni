package com.accounts.repository;

import com.accounts.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    Cuenta findByNumero(String nroCuenta);
    Cuenta findByClienteId(Long clienteId);
}
