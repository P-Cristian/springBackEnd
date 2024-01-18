package com.app.registration.service;

import com.app.registration.model._User;
import com.app.registration.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    @Autowired
    private RegistrationRepository repo;

    public _User saveUser(_User user){
        return repo.save(user);
    }

    public _User fetchUserByEmailId(String email)
    {
        return repo.findByEmailId(email);
    }
    public _User fetchUserByEmailAndPassword(String email,String password)
    {
        return repo.findByEmailIdAndPassword(email,password);
    }
}
