package com.example.projecturl.urlshortener.controller;

import com.example.projecturl.urlshortener.dto.UpdateUrlRequest;
import com.example.projecturl.urlshortener.dto.UrlRequest;
import com.example.projecturl.urlshortener.dto.UrlResponse;
import com.example.projecturl.urlshortener.dto.dashboardResponse;
import com.example.projecturl.urlshortener.entity.Url;
import com.example.projecturl.urlshortener.repository.UrlRepository;
import com.example.projecturl.urlshortener.service.UrlService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
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
    public UrlResponse createShortUrl(@Valid @RequestBody UrlRequest req,
                                      Authentication authentication)
    {
        return urlService.createShortUrl(req, authentication.getName());
    }
    @GetMapping("/my-urls")
    public Page<UrlResponse> getAllUrls(Authentication authentication, @RequestParam(defaultValue = "0") int page,@RequestParam (defaultValue = "5") int size)
    {
        return urlService.getMyUrls(authentication.getName(),page,size);
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

    @GetMapping("/dashboard")
    public dashboardResponse getDashBoard(Authentication authentication){
        return urlService.getDashBoard(authentication.getName());
    }

}
