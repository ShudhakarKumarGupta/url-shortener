package com.example.urlshortener.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.urlshortener.entity.UrlEntity;
import com.example.urlshortener.repository.UrlRepository;

@Service
public class UrlService {

    // Base62 characters
    private static final String BASE62 =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    @Autowired
    private UrlRepository urlRepository;

    // Create Short URL
    public String shortenUrl(String longUrl) {

        // Step 1: Save long URL to DB
        UrlEntity entity = new UrlEntity();
        entity.setLongUrl(longUrl);
        entity = urlRepository.save(entity);

        // Step 2: Encode DB ID to Base62
        String shortCode = encodeBase62(entity.getId());

        // Step 3: Save short code
        entity.setShortCode(shortCode);
        urlRepository.save(entity);

        return "http://localhost:8080/" + shortCode;
    }

    // Get Long URL
    public String getLongUrl(String shortCode) {
        return urlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new RuntimeException("URL not found"))
                .getLongUrl();
    }

    // Base62 Encoding Logic
    private String encodeBase62(Long id) {
        StringBuilder sb = new StringBuilder();

        while (id > 0) {
            sb.append(BASE62.charAt((int) (id % 62)));
            id = id / 62;
        }
        return sb.reverse().toString();
    }
}
