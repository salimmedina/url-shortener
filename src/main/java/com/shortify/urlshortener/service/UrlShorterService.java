package com.shortify.urlshortener.service;

import com.shortify.urlshortener.model.ShortenedUrl;
import com.shortify.urlshortener.repository.UrlShorterRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UrlShorterService {

    private final UrlShorterRepository urlShorterRepository;

    @Value("${urlshortener.baseshorturlpath}")
    public String BASE_SHORT_URL_PATH;


    public UrlShorterService(UrlShorterRepository urlShorterRepository) {
        this.urlShorterRepository = urlShorterRepository;
    }

    public String shortenURL(String longURL) {
        var optionalUrlShorter = urlShorterRepository.findByLongURL(longURL);
        if (optionalUrlShorter.isPresent()) {
            return buildShortURL(optionalUrlShorter.get().getShortURLId());
        }

        var shortUrlId = UUID.randomUUID().toString();
        var urlShorter = urlShorterRepository.save(new ShortenedUrl(longURL, shortUrlId));
        return buildShortURL(urlShorter.getShortURLId());
    }

    public Optional<String> getLongURL(String shortURLId) {
        return urlShorterRepository.findByShortURLId(shortURLId)
                .map(ShortenedUrl::getLongURL);
    }

    private String buildShortURL(String shortURLId) {
        return BASE_SHORT_URL_PATH + "/" + shortURLId;
    }
}
