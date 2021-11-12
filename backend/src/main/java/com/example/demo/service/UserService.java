package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.dto.CreateUserReqDto;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public User CreateUser(CreateUserReqDto userInfo) {
        User user = User.builder()
                .email(userInfo.getEmail())
                .name(userInfo.getName())
                .password(passwordEncoder.encode(userInfo.getPassword()))
                .build();
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User readUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(NotFoundException::new);
    }



}
