package com.example.urlshortener.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.urlshortener.entity.UrlEntity;

@Repository
public interface UrlRepository extends JpaRepository<UrlEntity, Long> {

    // Find long URL using short code
    Optional<UrlEntity> findByShortCode(String shortCode);
}
