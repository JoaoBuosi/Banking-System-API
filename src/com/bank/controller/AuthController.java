package com.bank.controller;

import com.bank.security.JwtUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> body) {
        String token = JwtUtil.gerarToken(body.get("username"));
        return Map.of("token", token);
    }
}
