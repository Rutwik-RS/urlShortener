package com.example.projecturl.urlshortener.service;


import com.example.projecturl.urlshortener.dto.UrlRequest;
import com.example.projecturl.urlshortener.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    public String createShortCode(UrlRequest req)
    {

    }

}
