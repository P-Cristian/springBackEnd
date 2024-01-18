package com.app.registration.repository;


import com.app.registration.model._User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<_User, Long> {
    //watch naming conventions , didnt like findbyEmailId for some reason
    _User findByEmailId(String emailId);

    _User findByEmailIdAndPassword(String email, String password);
}
