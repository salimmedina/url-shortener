package com.shortify.urlshortener.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public record ShortenedUrl(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id,
        String longURL,
        String shortURLId
) {
        public ShortenedUrl{
                if (shortURLId() == null){
                        shortURLId = UUID.randomUUID().toString();
                }

        }

        public static ShortenedUrl of(String longUrl){
                return new ShortenedUrl(null,longUrl,null);
        }
}

