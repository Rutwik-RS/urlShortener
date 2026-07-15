package com.example.projecturl.urlshortener.controller;

import com.example.projecturl.urlshortener.dto.UrlRequest;
import com.example.projecturl.urlshortener.dto.UrlResponse;
import com.example.projecturl.urlshortener.entity.Url;
import com.example.projecturl.urlshortener.repository.UrlRepository;
import com.example.projecturl.urlshortener.service.UrlService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/url")
public class UrlController {
    private UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/create")
    private UrlResponse createShortUrl(@RequestBody UrlRequest req)
    {
        return urlService.createShortUrl(req);

    }

}
