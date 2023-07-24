package com.accounts.app.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CuentaRequest(
        //Estos son datos obligatorios, asi que los vamos a marcar para que spring los
        //valide de entrada

        @NotBlank(message = "Moneda invalida")
        //la moneda tiene que ser si o si USD|BOB|UFV
        @Pattern(regexp = "^(USD|BOB|UFV)$", message = "Moneda invalida")
        String moneda,
        @Min(value = 1, message = "Cliente invalido")
        Long clienteId
) {
}
