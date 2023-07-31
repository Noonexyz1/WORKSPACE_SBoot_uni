package com.accounts.app.request;

import java.math.BigDecimal;

public record TransferenciaRequest(
        Long origenID, //del cliente
        Long destinatarioId,
        BigDecimal monto,
        String moneda
) {

}
