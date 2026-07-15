package com.example.projecturl.urlshortener.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="Urls")
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private Long id;
    @Column(name="originalUrl",nullable = false)
    private String originalUrl;
    @Column(name="shortCode",nullable = false,unique = true)
    private String shortCode;
    @Column(name="clickCount")
    private Long clickCount =0L;
    @Column(name="createdAt")
    private LocalDateTime createdAt;
    @Column(name="isActive")
    private boolean isActive = true;
    @Column(name="expiresAt")
    private LocalDateTime expiresAt;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public Url(Long id, String originalUrl, String shortCode, Long clickCount, LocalDateTime createdAt, boolean isActive, LocalDateTime expiresAt, User user) {
        this.id = id;
        this.originalUrl = originalUrl;
        this.shortCode = shortCode;
        this.clickCount = clickCount;
        this.createdAt = createdAt;
        this.isActive = isActive;
        this.expiresAt = expiresAt;
        this.user = user;
    }

    public Url() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public Long getClickCount() {
        return clickCount;
    }

    public void setClickCount(Long clickCount) {
        this.clickCount = clickCount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Url{" +
                "id=" + id +
                ", originalUrl='" + originalUrl + '\'' +
                ", shortCode='" + shortCode + '\'' +
                ", clickCount=" + clickCount +
                ", createdAt=" + createdAt +
                ", isActive=" + isActive +
                ", expiresAt=" + expiresAt +
                ", user=" + user +
                '}';
    }
}
