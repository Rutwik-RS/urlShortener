package com.example.projecturl.urlshortener.controller;

import com.example.projecturl.urlshortener.dto.UpdateUrlRequest;
import com.example.projecturl.urlshortener.dto.UrlRequest;
import com.example.projecturl.urlshortener.dto.UrlResponse;
import com.example.projecturl.urlshortener.entity.Url;
import com.example.projecturl.urlshortener.repository.UrlRepository;
import com.example.projecturl.urlshortener.service.UrlService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/url")
public class UrlController {
    private UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/create")
    public UrlResponse createShortUrl(@RequestBody UrlRequest req,
                                      Authentication authentication)
    {
        return urlService.createShortUrl(req, authentication.getName());
    }
    @GetMapping("/my-urls")
    public List<UrlResponse> getAllUrls(Authentication authentication)
    {
        return urlService.getMyUrls(authentication.getName());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUrl(@PathVariable Long id, Authentication authentication)
    {
        urlService.deleteUrl(id,authentication.getName());
        return ResponseEntity.ok("URL Successfully deleted!");
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<UrlResponse> updateUrl(@PathVariable Long id, Authentication authentication, @RequestBody UpdateUrlRequest req)
    {
        UrlResponse response =  urlService.updateUrl(authentication.getName(),id,req);
        return ResponseEntity.ok(response);
    }




}
