package com.app.registration.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

// User was reserved had to use _User , H2 stuff
@Entity
public class _User extends SecurityProperties.User {
    @Id
    private int id;
    private String emailId;
    private String userName;
    private String password;

    public int getId() {
        return id;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public _User(int id, String emailId, String userName, String password) {
        this.id = id;
        this.emailId = emailId;
        this.userName = userName;
        this.password = password;
    }

    public _User() {

    }
}