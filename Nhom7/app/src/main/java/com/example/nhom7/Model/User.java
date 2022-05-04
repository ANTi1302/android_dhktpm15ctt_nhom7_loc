package com.example.nhom7.Model;

public class User {
    private String Name, Password,Phone;

    public User() {
    }

    public User(String name, String password) {
        Name = name;
        Password = password;

    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getName() {
        return Name;
    }

    public String getPassword() {
        return Password;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public void setPassword(String password) {
        this.Password = password;
    }
}
