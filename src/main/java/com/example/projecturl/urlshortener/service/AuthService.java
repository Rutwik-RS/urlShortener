package com.example.projecturl.urlshortener.service;
import com.example.projecturl.urlshortener.dto.AuthResponse;
import com.example.projecturl.urlshortener.dto.LoginRequest;
import com.example.projecturl.urlshortener.dto.RegisterRequest;
import com.example.projecturl.urlshortener.entity.User;
import com.example.projecturl.urlshortener.repository.UserRepository;
import com.example.projecturl.urlshortener.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthService {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(JwtService jwtService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public String register(RegisterRequest req){
        User user = new User();
        user.setUsername(req.getUsername());
        String hashedPassword = passwordEncoder.encode(req.getPassword());
        user.setPassword(hashedPassword);
        user.setEmail(req.getEmail());
        user.setCreatedAt(LocalDateTime.now());
        if(userRepository.findByEmail(req.getEmail()).isPresent() )
            return "User with this email already exists!";
        userRepository.save(user);
        return "User Registered Successfully!";
    }
    public AuthResponse login(LoginRequest lreq)
    {
        Optional<User> optionalUser =  userRepository.findByEmail(lreq.getEmail());
        if(optionalUser.isPresent())
        {
            User user = optionalUser.get();
            if(passwordEncoder.matches(lreq.getPassword(),user.getPassword()))
            {
                return new AuthResponse(jwtService.generateToken(user.getEmail()));
            }
        }
         throw new RuntimeException("Invalid Email or Password");
    }
}
