package com.example.demo.service;

import com.example.demo.dto.UserLoginRequest;
import com.example.demo.dto.UserRegisterRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 1. 회원가입
    @Transactional
    public Long register(UserRegisterRequest request) {
        if (request.getUsername() == null || request.getPassword() == null || request.getNickname() == null) {
            throw new IllegalArgumentException("필수 입력 항목이 누락되었습니다.");
        }

        userRepository.findByUsername(request.getUsername())
                .ifPresent(u -> {
                    throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
                });

        User user = new User(request.getUsername(), request.getPassword(), request.getNickname());
        return userRepository.save(user).getId();
    }

    // 2. 로그인 검증
    public UserResponse login(UserLoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디입니다."));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return new UserResponse(user);
    }

    // 3. 회원 단건 조회
    public UserResponse getProfile(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        return new UserResponse(user);
    }
}