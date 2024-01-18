package com.app.registration.controller;

import com.app.registration.model._User;
import com.app.registration.service.RegistrationService;
import org.apache.catalina.User;
import org.apache.catalina.valves.JsonErrorReportValve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.WebExceptionHandler;
import org.springframework.http.ResponseEntity;


@RestController
public class RegistrationController {
    @Autowired
    private RegistrationService service;

    @PostMapping("/registeruser")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> registerUser(@RequestBody _User user) {
        String tempEmailId = user.getEmailId();

        try {
            if (tempEmailId != null && !"".equals(tempEmailId)) {
                _User existingUser = service.fetchUserByEmailId(tempEmailId);

                if (existingUser != null) {
                    throw new UserAuthenticationException("User with " + tempEmailId + " already exists");
                }
            } else {
                throw new UserAuthenticationException("Email ID cannot be null or empty");
            }

            _User registeredUser = service.saveUser(user);
            return ResponseEntity.ok(registeredUser);
        } catch (UserAuthenticationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> loginUser(@RequestBody _User user) {
        String tempEmailId = user.getEmailId();
        String tempPassword = user.getPassword();
        _User userObj = null;

        try {
            if (tempEmailId != null && tempPassword != null) {
                userObj = service.fetchUserByEmailAndPassword(tempEmailId, tempPassword);
            }

            if (userObj == null) {
                throw new UserAuthenticationException("Invalid email or password");
            }

            return ResponseEntity.ok(userObj);
        } catch (UserAuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}

