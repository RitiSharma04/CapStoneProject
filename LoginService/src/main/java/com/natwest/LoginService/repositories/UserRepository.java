package com.natwest.LoginService.repositories;


import com.natwest.LoginService.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<com.natwest.LoginService.entity.User,Integer> {


    Optional<User> findByEmail(String email);

}
