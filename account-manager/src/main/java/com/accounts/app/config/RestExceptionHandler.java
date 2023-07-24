package com.accounts.app.config;


import com.accounts.commons.api.ApiError;
import com.accounts.commons.enums.ErrMsg;
import com.accounts.commons.exceptions.BadRequesException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import org.hibernate.type.descriptor.java.ObjectJavaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<Object> handlerHttpMessageNotReadable(HttpMessageNotReadableException ex){
                                    //los codigos de errores "001" de cadena no esta bien, mejor nos hacermos un fichero donde poner los codigos de errores y sus mensajes ENUM
        return  buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, ErrMsg.JSON_PROCESSING.getCode(), ErrMsg.JSON_PROCESSING.getMessage()));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handelMetohArgumentNotValid(MethodArgumentNotValidException ex){
        List<String> list = ex.getBindingResult().getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());
                                                                                                                //Esto es Tarea, si lo hize bien o no, ya no se hace uso de la Lista
        return  buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, ErrMsg.ARG_NOT_VALID.getCode(), ErrMsg.ARG_NOT_VALID.getMessage()));
    }
    @ExceptionHandler(BadRequesException.class)
    public ResponseEntity<Object> handelMetohNameRepeated(BadRequesException ex){
        return  buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, ErrMsg.NAME_IS_NOT_VALID.getCode(), ErrMsg.NAME_IS_NOT_VALID.getMessage())); //asi si puede ser
    }

@ExceptionHandler(FeignException.class)
    protected ResponseEntity<Object> handlerMethodFeignException(FeignException ex){
        try{
            /*ObjetMapper mapea lo que sea a objeto y de objeto a lo que sea*/
            ObjectMapper mapper = new ObjectMapper();
            ApiError apiError = mapper.readValue(ex.contentUTF8(), ApiError.class);
            return buildResponseEntity(new ApiError(HttpStatus.valueOf(ex.status()), apiError.getCode(),apiError.getMessage()));
        }catch (Exception e){
            return buildResponseEntity(new ApiError(HttpStatus.NOT_ACCEPTABLE, ErrMsg.CNN_REFUSED.getCode(),ErrMsg.CNN_REFUSED.getMessage()));
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex){
        //return  buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, "999", ex.getMessage()));  ex.getMessage() ASI NO, TE PUEDEN PEGAR POR MOSTRAR DATOS DEL PROYECTO
        //lanza cualquier Exception para que funcione esta parte.
        //me Lanza un ErrMsg.GENERIC_ERROR cuando en realidad estoy enviando un objeto con el mismo nombre
        return  buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ErrMsg.GENERIC_ERROR.getCode(), ErrMsg.GENERIC_ERROR.getMessage())); //asi si puede ser
    }


    public ResponseEntity<Object> buildResponseEntity(ApiError apiError){
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }


}
