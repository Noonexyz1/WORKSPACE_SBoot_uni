package com.accounts.commons.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {
    @JsonIgnore
    private HttpStatus status;
    private String code;
    private String message;


    //Esto es un DTO, que se transferira al cliente


}
