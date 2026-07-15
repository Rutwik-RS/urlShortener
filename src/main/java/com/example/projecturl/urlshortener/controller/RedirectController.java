package com.example.projecturl.urlshortener.controller;

import com.example.projecturl.urlshortener.entity.Url;
import com.example.projecturl.urlshortener.service.UrlService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
@RestController
public class RedirectController {

    private final  UrlService urlService;

    public RedirectController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(@PathVariable String shortCode){
        Url url = urlService.redirect(shortCode);
        if(url == null)
        {
            return ResponseEntity.notFound().build();
        }
        URI uri  =  URI.create(url.getOriginalUrl());
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .location(uri)
                .build();
    }

}
