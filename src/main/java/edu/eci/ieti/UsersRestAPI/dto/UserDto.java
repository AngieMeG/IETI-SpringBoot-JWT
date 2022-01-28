package edu.eci.ieti.UsersRestAPI.dto;

public class UserDto {

    private String name;
    private String email;
    private String lastName;

    public UserDto(){

    }

    public void setName(String name){
        this.name = name;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setLastname(String lastName){
        this.lastName = lastName;
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

    public String toString(){
        return "Name: " + name + ", Email: " + email + ", Last Name:" + lastName;
    }
}
