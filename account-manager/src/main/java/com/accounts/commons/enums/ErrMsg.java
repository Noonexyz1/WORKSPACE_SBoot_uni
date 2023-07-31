package com.accounts.commons.enums;

public enum ErrMsg {

    JSON_PROCESSING("001", "Json mal formado"),
    ARG_NOT_VALID("002", "Argumento no valido"),
    NAME_IS_NOT_VALID("003", "El nombre ya se encuentra registrado"),
    BAT_REQUEST_CLIENT("004", "No hemos podido encontrar el Cliente"),
    CNN_REFUSED("998", "Error de Comunicacion"),
    GENERIC_ERROR("999", "Communiquese con Sistemas"),
    CUENTA_NO_VALIDA("005", "Numero de cuenta invalida"),
    SALDO_INSUFICIENTE("007", "Saldo Insuficiente"),
    MONEDA_INVALIDA("006", "Moneda invalida");

    private final String code;
    private final String message;

    private ErrMsg(String code, String message){
        this.code = code;
        this.message = message;
    }



    public String getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }

}
