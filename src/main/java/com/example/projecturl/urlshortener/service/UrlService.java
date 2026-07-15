package com.example.projecturl.urlshortener.service;


import com.example.projecturl.urlshortener.dto.UrlRequest;
import com.example.projecturl.urlshortener.dto.UrlResponse;
import com.example.projecturl.urlshortener.entity.Url;
import com.example.projecturl.urlshortener.repository.UrlRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    private String generateShortCode() {

        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
       while(true){
        StringBuilder shortCode = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int idx = (int) (Math.random() * ((characters.length() - 1) - 0 + 1)) + 0;
            char ch = characters.charAt(idx);
            shortCode.append(ch);
        }
        if(!urlRepository.findByShortCode(shortCode.toString()).isPresent()) {
            return shortCode.toString();
        }
    }
    }

    public UrlResponse createShortUrl(UrlRequest req){
        String shortCode = generateShortCode();
        Url url = new Url();
        url.setOriginalUrl(req.getOriginalUrl());
        url.setShortCode(shortCode);
        url.setCreatedAt(LocalDateTime.now());
        url.setActive(true);
        url.setExpiresAt(LocalDateTime.now().plusDays(30));
        Url savedUrl = urlRepository.save(url);

        return new UrlResponse(
                savedUrl.getId(),
                "http://localhost:8080/" + savedUrl.getShortCode(),
                savedUrl.getShortCode(),
                savedUrl.getOriginalUrl(),
                0L,
                savedUrl.getCreatedAt(),
                savedUrl.getExpiresAt(),
                null
        );
    }

    public Url redirect(String shortCode)
    {
        Optional<Url> searchedUrl = urlRepository.findByShortCode(shortCode);
        if(searchedUrl.isEmpty())
        {
            return null;
        }
        Url url  = searchedUrl.get();
        if(!url.isActive())
        {
            return null;
        }
        if(LocalDateTime.now().isAfter(url.getExpiresAt()) )
        {
            return null;
        }
        url.setClickCount(url.getClickCount()+1);
        urlRepository.save(url);
        return url;
    }

}