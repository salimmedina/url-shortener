package com.shortify.urlshortener.repository;

import com.shortify.urlshortener.model.ShortenedUrl;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;


public interface UrlShorterRepository extends JpaRepository<ShortenedUrl, Long> {

    Optional<ShortenedUrl> findByLongURL(String longURL);
    Optional<ShortenedUrl> findByShortURLId(String shortURLId);

}
