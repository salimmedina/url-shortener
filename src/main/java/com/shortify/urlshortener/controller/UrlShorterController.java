package com.shortify.urlshortener.controller;

import com.shortify.urlshortener.service.UrlShorterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UrlShorterController {

    @Autowired
    private UrlShorterService urlShorterService;

    @PostMapping("/v1/short")
    public String shortenUrl(@RequestParam("longUrl") String longURL){
        return urlShorterService.shortenURL(longURL);
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity redirect(@PathVariable("shortUrl") String shortURL) {
        return urlShorterService.getLongURL(shortURL)
                .map(longUrl -> ResponseEntity
                        .status(HttpStatus.MOVED_PERMANENTLY)
                        .header(HttpHeaders.LOCATION, longUrl)
                        .build())
                .orElseGet(() -> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .build());
}
}
