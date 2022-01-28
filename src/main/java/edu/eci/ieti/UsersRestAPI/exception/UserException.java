package edu.eci.ieti.UsersRestAPI.exception;

public class UserException extends Exception {

    public static final String CREATE_USER_EXCEPTION = "The user has already been created.";
    public static final String USER_DOESNT_EXIST = "The user does not exist't exist.";
    
    public UserException(String message) {
        super(message);
    }
}
