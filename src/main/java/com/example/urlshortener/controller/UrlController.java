package com.example.urlshortener.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.urlshortener.service.UrlService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class UrlController {

    @Autowired
    private UrlService urlService;

    // API 1: Create short URL
    @PostMapping("/shorten")
    public String shortenUrl(@RequestParam String longUrl) {
        return urlService.shortenUrl(longUrl);
    }

    // API 2: Redirect to long URL
    @GetMapping("/{shortCode}")
    public void redirect(@PathVariable String shortCode,
                         HttpServletResponse response) throws IOException {

        String longUrl = urlService.getLongUrl(shortCode);
        response.sendRedirect(longUrl);
    }
}
