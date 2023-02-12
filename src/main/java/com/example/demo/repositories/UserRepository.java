package com.example.demo.repositories;

import com.example.demo.entities.Users;
import lombok.NonNull;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNullApi;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends CrudRepository<Users,Long> {
    Optional<Users> getById(Long id);

    @Modifying
    @Transactional
    @Query(nativeQuery = true,
            value = "INSERT INTO users (username, email, password, enabled) select :userName,:email,:password,true")
    void saveUser (String userName,String email,String password);


    @Modifying
    @Transactional
    @Query(nativeQuery = true,
            value = "INSERT INTO authorities (username, authority) select :userName,:authority")
    void addRoles (String userName,String authority);

    @Modifying
    @Transactional
    @Query(nativeQuery = true,
            value = "UPDATE users (username, email) select :userName,:email")
    void updateUser(String userName, String email);
}
