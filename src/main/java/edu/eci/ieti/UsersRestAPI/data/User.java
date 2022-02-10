package edu.eci.ieti.UsersRestAPI.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import edu.eci.ieti.UsersRestAPI.dto.UserDto;

@Document
public class User {

    @Id
    private String id;

    private String name;

    @Indexed( unique = true )
    private String email;

    private String lastName;
    
    private String createdAt;

    public User(){

    }

    public User(UserDto user){
        name = user.getName();
        email = user.getEmail();
        lastName = user.getLastName();
    }

    public void setId(String id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public void setCreatedAt(String createdAt){
        this.createdAt = createdAt;
    }

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    public String getLastName(){
        return lastName;
    }

    public String getCreatedAt(){
        return createdAt;
    }

    public String toString(){
        return "Id: " + id + ", Name: " + name + ", Email: " + email + ", lastName: " + lastName + ", CreatedAt: " + createdAt;
    }

    public boolean equals(User user){
        return this.getEmail().equals(user.getEmail());
    }
}
