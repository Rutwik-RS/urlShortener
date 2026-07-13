package com.example.projecturl.urlshortener.controller;
import com.example.projecturl.urlshortener.dto.LoginRequest;
import com.example.projecturl.urlshortener.dto.RegisterRequest;
import com.example.projecturl.urlshortener.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authservice;

    @Autowired
    public AuthController(AuthService authservice){
        this.authservice = authservice;
    }
    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest req){
        return authservice.register(req);
    }
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest lreq)
    {
        return authservice.login(lreq);
    }
}
