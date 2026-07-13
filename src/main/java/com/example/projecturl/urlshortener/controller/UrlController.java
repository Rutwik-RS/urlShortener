package com.example.projecturl.urlshortener.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/url")
public class UrlController {



    @PostMapping("/create")
    public void createShortUrl(String originalUrl)
    {

    }


}
