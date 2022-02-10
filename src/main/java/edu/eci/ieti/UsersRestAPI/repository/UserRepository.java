package edu.eci.ieti.UsersRestAPI.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import edu.eci.ieti.UsersRestAPI.data.User;

public interface UserRepository extends MongoRepository<User, String> {

}
