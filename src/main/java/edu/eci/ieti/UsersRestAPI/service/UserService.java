package edu.eci.ieti.UsersRestAPI.service;

import java.util.List;

import edu.eci.ieti.UsersRestAPI.data.User;
import edu.eci.ieti.UsersRestAPI.exception.UserException;

public interface UserService {
    
    User create( User user ) throws UserException;

    User findById( String id ) throws UserException;

    List<User> getAll();

    void deleteById( String id ) throws UserException;

    User update( User user, String userId ) throws UserException;
}
