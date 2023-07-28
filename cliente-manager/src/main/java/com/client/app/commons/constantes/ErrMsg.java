package com.client.app.commons.constantes;

public enum ErrMsg {

    JSON_PROCESSING("001", "Json mal formado"),
    ARG_NOT_VALID("002", "Argumento no valido"),
    NAME_IS_NOT_VALID("003", "El nombre ya se encuentra registrado"),
    BAT_REQUEST_CLIENT("004", "No hemos podido encontrar el Cliente"),
    GENERIC_ERROR("999", "Communiquese con Sistemas");

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
