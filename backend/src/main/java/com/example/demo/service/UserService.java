package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.dto.CreateUserReqDto;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.utils.config.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public User CreateUser(CreateUserReqDto userInfo) {
        User user = User.builder()
                .userId(userInfo.getUserId())
                .name(userInfo.getName())
                .password(passwordEncoder.encode(userInfo.getPassword()))
                .build();
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User readUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(NotFoundException::new);
    }


    public Boolean idDuplicationCheck(String userId) {
        if(userRepository.findByUserId(userId).isPresent())
            return true;
        return false;
    }
}
