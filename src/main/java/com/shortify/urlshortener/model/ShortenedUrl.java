package com.shortify.urlshortener.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@RequiredArgsConstructor
public class ShortenedUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String longURL;
    private String shortURLId;

    public ShortenedUrl(String longURL, String shortURLId) {
        this.longURL = longURL;
        this.shortURLId = shortURLId;
    }
}

