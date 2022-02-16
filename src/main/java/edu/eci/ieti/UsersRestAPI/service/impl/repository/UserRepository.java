package edu.eci.ieti.UsersRestAPI.service.impl.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import edu.eci.ieti.UsersRestAPI.data.User;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<User, String> {
    @Query("email : ?0}")
    User getUserByEmail(String email);
}
