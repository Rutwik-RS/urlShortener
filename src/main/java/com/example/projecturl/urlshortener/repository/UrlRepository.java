package com.example.projecturl.urlshortener.repository;

import com.example.projecturl.urlshortener.entity.Url;
import com.example.projecturl.urlshortener.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UrlRepository extends JpaRepository<Url,Long> {
     Optional<Url> findByShortCode(String shortCode);
     Optional<Url> findByUserAndOriginalUrl(User user, String originalUrl);
     List<Url> findByUser(User user);
     Optional<Url> findById(Long Id);
     Page<Url> findByUser(User user, Pageable pageable);
}