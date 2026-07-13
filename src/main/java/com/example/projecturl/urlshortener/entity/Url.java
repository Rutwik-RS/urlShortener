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
    private Integer clickCount =0;
    @Column(name="createdAt")
    private LocalDateTime createdAt;
    @Column(name="isActive")
    private boolean isActive = true;
    @Column(name="expiresAt")
    private LocalDateTime expiresAt;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}
