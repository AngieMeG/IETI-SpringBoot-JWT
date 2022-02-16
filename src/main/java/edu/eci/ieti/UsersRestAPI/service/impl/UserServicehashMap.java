package edu.eci.ieti.UsersRestAPI.service.impl;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Qualifier;

import edu.eci.ieti.UsersRestAPI.data.User;
import edu.eci.ieti.UsersRestAPI.exception.UserException;
import edu.eci.ieti.UsersRestAPI.service.UserService;

@Service
@Qualifier("HashMapImpl")
public class UserServicehashMap implements UserService{

    HashMap<String, User> usersMap = new HashMap<>();
    private static final AtomicInteger generatedId = new AtomicInteger(0);
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");  
    
    @Override
    public User create(User user) throws UserException {
        for(User savedUser : usersMap.values()) {
            if (savedUser.equals(user)) {
                throw new UserException(UserException.CREATE_USER_EXCEPTION);
            }
        }
        user.setId(generatedId.incrementAndGet() + "");
        user.setCreatedAt(formatCurrentDateToString());
        usersMap.put(user.getId(), user);

        return user;
    }

    @Override
    public User findById(String id) throws UserException {
        User user = usersMap.get(id);
        if (user == null) throw new UserException(UserException.USER_DOESNT_EXIST);

        return usersMap.get(id);
    }

    @Override
    public User findByEmail(String email) throws UserException {
        return null;
    }

    @Override
    public List<User> getAll() {

        return new ArrayList<>(usersMap.values());
    }

    @Override
    public void deleteById(String id) throws UserException {
        int numberOfUsersBeforeOperation = usersMap.size();
        usersMap.remove(id);
        if(numberOfUsersBeforeOperation == usersMap.size()) throw new UserException(UserException.USER_DOESNT_EXIST);
    }

    @Override
    public User update(User user, String userId) throws UserException {
        User updatedUser = null;
        if(usersMap.containsKey(userId)){
            User unupdatedUser = usersMap.get(userId);
            user.setId(userId);
            user.setCreatedAt(unupdatedUser.getCreatedAt());
            usersMap.put(userId, user);
            updatedUser = user;
        } else{throw new UserException(UserException.USER_DOESNT_EXIST);}

        return updatedUser;
    }
    
    private String formatCurrentDateToString(){

        return dtf.format(LocalDateTime.now()).toString();
    }
}
