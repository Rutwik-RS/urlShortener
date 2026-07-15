package com.example.projecturl.urlshortener.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;

    public JwtFilter(JwtService jwtService, CustomUserDetailsService customUserDetailsService) {
        this.jwtService = jwtService;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if(authHeader == null|| !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        System.out.println("JWT Filter Executed");

        String jwt = authHeader.substring(7);
        if(!jwtService.validateToken(jwt))
        {
            filterChain.doFilter(request, response);
            return;
        }
        System.out.println("1");

        String email = jwtService.extractEmail(jwt);

        System.out.println("2");

        UserDetails userDetails =
                customUserDetailsService.loadUserByUsername(email);

        System.out.println("3");

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

        System.out.println("4");
        System.out.println("Before: " +
                SecurityContextHolder.getContext().getAuthentication());
        if(SecurityContextHolder.getContext().getAuthentication() == null)
        {
            SecurityContextHolder.getContext()
                    .setAuthentication(authentication);
        }

        System.out.println("After: " +
                SecurityContextHolder.getContext().getAuthentication());
        filterChain.doFilter(request,response);
    }

}