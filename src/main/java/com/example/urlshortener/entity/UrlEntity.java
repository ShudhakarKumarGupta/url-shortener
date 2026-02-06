package com.example.urlshortener.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "url_mapping")
public class UrlEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Auto generated ID

    @Column(unique = true)
    private String shortCode; // Short URL code

    @Column(nullable = false, length = 2000)
    private String longUrl; // Original long URL

    // getters and setters
    public Long getId() {
        return id;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }
}
