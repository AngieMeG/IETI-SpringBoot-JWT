package edu.eci.ieti.UsersRestAPI.exception;

import edu.eci.ieti.UsersRestAPI.data.ErrorCodeEnum;
import edu.eci.ieti.UsersRestAPI.dto.ServerErrorResponseDto;
import org.springframework.http.HttpStatus;

import javax.ws.rs.InternalServerErrorException;

public class InvalidCredentialsException extends InternalServerErrorException {

    public InvalidCredentialsException() {
        super(String.valueOf(new ServerErrorResponseDto("User not found", ErrorCodeEnum.USER_NOT_FOUND, HttpStatus.NOT_FOUND)));
    }
}
