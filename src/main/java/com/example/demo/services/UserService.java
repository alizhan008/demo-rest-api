package com.example.demo.services;

import com.example.demo.dto.UsersDto;
import com.example.demo.entities.Users;
import com.example.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public Optional<UsersDto> getUserById(Long id) {
        Optional<Users> users = userRepository.getById(id);
        return Optional.ofNullable(UsersDto.builder()
                        .id(users.get().getId())
                        .userName(users.get().getUserName())
                        .email(users.get().getEmail())
                        .build());
    }


    public void saveUser(UsersDto user) {
        userRepository.saveUser(
                user.getUserName(),
                user.getEmail(),
                passwordEncoder().encode(user.getPassword())
        );
    }

    public void updateUser(UsersDto user) {
        userRepository.updateUser(
                user.getUserName(),
                user.getEmail());
    }
    public void addRoles (String userName,String authority){
        userRepository.addRoles(userName,authority);
    }
}
