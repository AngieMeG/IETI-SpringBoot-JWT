package edu.eci.ieti.UsersRestAPI.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;

import edu.eci.ieti.UsersRestAPI.data.User;
import edu.eci.ieti.UsersRestAPI.exception.UserException;
import edu.eci.ieti.UsersRestAPI.repository.UserRepository;
import edu.eci.ieti.UsersRestAPI.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("MongoImpl")
public class UserServiceMongoDB implements UserService{

    private final UserRepository userRepository;
    private static final AtomicInteger generatedId = new AtomicInteger(0);
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");  
    
    public UserServiceMongoDB(@Autowired UserRepository userRepository ){
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) throws UserException {
        for(User savedUser : getAll()) {
            if (savedUser.equals(user)) {
                throw new UserException(UserException.CREATE_USER_EXCEPTION);
            }
        }
        user.setId(generatedId.incrementAndGet() + "");
        user.setCreatedAt(dtf.format(LocalDateTime.now()).toString());
        userRepository.save(user);

        return user;
    }

    @Override
    public User findById(String id) throws UserException {
        User user = userRepository.findById(id).get();
        if (user == null) throw new UserException(UserException.USER_DOESNT_EXIST);

        return user;
    }

    @Override
    public User findByEmail(String email) throws UserException {
        User user = userRepository.getUserByEmail(email);
        if (user == null) throw new UserException(UserException.USER_DOESNT_EXIST);

        return user;
    }

    @Override
    public List<User> getAll() {

        return userRepository.findAll();
    }

    @Override
    public void deleteById(String id) throws UserException {
        int numberOfUsersBeforeOperation = (int) userRepository.count();
        userRepository.deleteById(id);
        if(numberOfUsersBeforeOperation == (int) userRepository.count()) throw new UserException(UserException.USER_DOESNT_EXIST);
        
    }

    @Override
    public User update(User user, String userId) throws UserException {
        User updatedUser = null;
        User unupdatedUser = findById(userId);;
        user.setId(userId);
        user.setCreatedAt(unupdatedUser.getCreatedAt());
        userRepository.save(user);
        updatedUser = user;
        return updatedUser;
    }
    
}
