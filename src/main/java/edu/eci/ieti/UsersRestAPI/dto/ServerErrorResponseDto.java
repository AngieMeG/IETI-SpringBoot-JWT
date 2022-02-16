package edu.eci.ieti.UsersRestAPI.dto;

import edu.eci.ieti.UsersRestAPI.data.ErrorCodeEnum;
import org.springframework.http.HttpStatus;

public  class  ServerErrorResponseDto {

    String  message;
    ErrorCodeEnum errorCode;
    int  httpStatus;

    public  ServerErrorResponseDto(String  message, ErrorCodeEnum  errorCode, HttpStatus httpStatus) {
        this.message = message;
        this.errorCode = errorCode;
        this.httpStatus = httpStatus.value();
    }
    public  String  getMessage() {
        return  message;
    }

    public  ErrorCodeEnum  getErrorCode() {
        return  errorCode;
    }

    public  int  getHttpStatus() {
        return  httpStatus;
    }

}