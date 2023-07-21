package com.client.app.commons.exceptions;

import lombok.Getter;

@Getter
public class BadRequesException extends RuntimeException{  //Mi Exception Personalizada =)
    private String code;

    public BadRequesException() {
        super();
    }
    public BadRequesException(String message) {
        super(message);
    }

    public BadRequesException(String code, String message) {
        super(message);
        this.code = code;
    }

}
