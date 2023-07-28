package com.accounts.provider;


import com.accounts.provider.record.Cliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//nombre del microservicio
@FeignClient(name = "client-manager", url = "localhost:8080/clientes")
public interface ClientesProvider {
    @GetMapping("/{id}")
    Cliente detalle(@PathVariable Long id);
    @GetMapping("/{id}")
    boolean existeCliente(@PathVariable Long id);

}
