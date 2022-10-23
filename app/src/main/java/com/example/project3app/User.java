package com.example.project3app;

public class User {

    public String name, email,cell, password;

    public User(){

    }

    public User(String name, String email, String cell, String password) {
        this.name = name;
        this.email = email;
        this.cell = cell;
        this.password = password;
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

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
