package com.example.projecturl.urlshortener.service;


import com.example.projecturl.urlshortener.dto.UpdateUrlRequest;
import com.example.projecturl.urlshortener.dto.UrlRequest;
import com.example.projecturl.urlshortener.dto.UrlResponse;
import com.example.projecturl.urlshortener.entity.Url;
import com.example.projecturl.urlshortener.entity.User;
import com.example.projecturl.urlshortener.repository.UrlRepository;
import com.example.projecturl.urlshortener.repository.UserRepository;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UrlService {

    private final UrlRepository urlRepository;
    private final UserRepository userRepository;

    public UrlService(UrlRepository urlRepository, UserRepository userRepository) {
        this.urlRepository = urlRepository;
        this.userRepository = userRepository;
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

    public UrlResponse createShortUrl(UrlRequest req, String email){

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Optional<Url> existing =
                urlRepository.findByUserAndOriginalUrl(user, req.getOriginalUrl());

        if (existing.isPresent()) {
            return mapToResponse(existing.get());
        }
        String shortCode = generateShortCode();
        Url url = new Url();
        url.setOriginalUrl(req.getOriginalUrl());
        url.setShortCode(shortCode);
        url.setCreatedAt(LocalDateTime.now());
        url.setActive(true);
        url.setExpiresAt(LocalDateTime.now().plusDays(30));
        url.setUser(user);
        Url savedUrl = urlRepository.save(url);

        return mapToResponse(savedUrl);

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
    private UrlResponse mapToResponse(Url url) {
        return new UrlResponse(
                url.getId(),
                "http://localhost:8080/" + url.getShortCode(),
                url.getShortCode(),
                url.getOriginalUrl(),
                url.getClickCount(),
                url.getCreatedAt(),
                url.getExpiresAt(),
                url.getUser().getUsername()
        );
    }
    public   List<UrlResponse> getMyUrls(String email)
    {
        User user = userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("User not found!"));
        List<Url> urls = urlRepository.findByUser(user);
        return urls.stream()
                .map(this::mapToResponse)
                .toList();
    }
    public void deleteUrl(Long id,String email)
    {
        User user= userRepository.findByEmail(email).orElseThrow(()->new RuntimeException(("User not found!")));
        Url url = urlRepository.findById(id).orElseThrow(()->new RuntimeException(("Url not found!")));
        if(!Objects.equals(url.getUser().getId(), user.getId()))
        {
            throw new RuntimeException("You are not allowed to delete this url!");
        }
        urlRepository.delete(url);

    }
    public UrlResponse updateUrl(String email, Long id, UpdateUrlRequest req)
    {
        User user  = userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("User not found!"));
        Url url = urlRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Url Not FOund!"));
        if(!user.getId().equals(url.getUser().getId()))
        {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"You Cannot edit this url as you're not authorized!");
        }
        if(req.getOriginalUrl()!=null) {
            url.setOriginalUrl(req.getOriginalUrl());
        }
        if(req.getExpiresAt()!=null) {
            url.setExpiresAt(req.getExpiresAt());
        }
        Url updatedUrl = urlRepository.save(url);
        return mapToResponse(updatedUrl);
    }

    public void getDashBoard(String email){
        User user = userRepository.findByEmail(email).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found"));
        List<Url> urls = urlRepository.findByUser(user);
        long total  = urls.size();
        long activeUrls  = urls.stream().filter(Url::isActive).count();
        long expiredUrls =  urls.stream().filter(url-> url.getExpiresAt()!=null && url.getExpiresAt().isBefore(LocalDateTime.now())).count();
    }
}