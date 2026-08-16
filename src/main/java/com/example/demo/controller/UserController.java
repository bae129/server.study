package com.example.demo.controller;

import com.example.demo.dto.UserLoginRequest;
import com.example.demo.dto.UserRegisterRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    public static final String SESSION_KEY = "LOGIN_USER";
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원가입
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterRequest request) {
        try {
            Long userId = userService.register(request);
            return ResponseEntity.ok(userId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 로그인 (세션 생성 및 사용자 정보 저장)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest request, HttpServletRequest httpRequest) {
        try {
            UserResponse loginUser = userService.login(request);

            // 기존 세션이 있으면 반환하고, 없으면 신규 세션을 생성
            HttpSession session = httpRequest.getSession(true);
            session.setAttribute(SESSION_KEY, loginUser);

            return ResponseEntity.ok(loginUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 로그아웃 (세션 무효화)
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest httpRequest) {
        HttpSession session = httpRequest.getSession(false);
        if (session != null) {
            session.invalidate(); // 세션 삭제
        }
        return ResponseEntity.ok("로그아웃되었습니다.");
    }

    // 현재 로그인된 사용자 정보 확인
    @GetMapping("/me")
    public ResponseEntity<?> getMyInfo(HttpServletRequest httpRequest) {
        HttpSession session = httpRequest.getSession(false);
        if (session == null || session.getAttribute(SESSION_KEY) == null) {
            return ResponseEntity.status(401).body("로그인 상태가 아닙니다.");
        }

        UserResponse loginUser = (UserResponse) session.getAttribute(SESSION_KEY);
        return ResponseEntity.ok(loginUser);
    }

    // 회원 정보 조회
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getProfile(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getProfile(id));
    }
}