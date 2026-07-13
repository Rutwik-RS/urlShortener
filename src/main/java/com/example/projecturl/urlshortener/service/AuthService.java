package com.example.projecturl.urlshortener.service;
import com.example.projecturl.urlshortener.dto.LoginRequest;
import com.example.projecturl.urlshortener.dto.RegisterRequest;
import com.example.projecturl.urlshortener.entity.User;
import com.example.projecturl.urlshortener.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public AuthService(UserRepository userRepository,PasswordEncoder passwordEncoder){
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
    public String login(LoginRequest lreq)
    {
        Optional<User> optionalUser =  userRepository.findByEmail(lreq.getEmail());
        if(optionalUser.isPresent())
        {
            User user = optionalUser.get();
            if(passwordEncoder.matches(lreq.getPassword(),user.getPassword()))
            {
                return "Login Successfull!";
            }
        }
        return "Invalid Email or Password";
    }
}
