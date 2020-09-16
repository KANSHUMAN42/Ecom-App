package com.example.ecomapp.loginRegister;

public class Userdetails {
    String name ;
     String email;
     String number;
    String password;
    String user_name;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Userdetails(){

  }

    public Userdetails(String name, String email, String number, String password,String user_name) {
        this.name = name;
        this.email = email;
        this.number = number;
        this.password = password;
        this.user_name=user_name;
    }


}
