package com.accounts.app.response;

import com.accounts.commons.enums.Moneda;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record CuentaResponse(

        //mostramos solo los datos que el cliente necesite saber
        String numero,
        Moneda moneda,
        BigDecimal saldoDisponible,
        String habilitado

) {

}
