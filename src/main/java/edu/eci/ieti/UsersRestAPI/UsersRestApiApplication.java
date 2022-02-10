package edu.eci.ieti.UsersRestAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class UsersRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersRestApiApplication.class, args);
	}

}
