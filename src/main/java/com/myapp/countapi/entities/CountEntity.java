package com.myapp.countapi.entities;

import jakarta.persistence.*;

import java.math.BigInteger;

@Entity(name = "count_api")
public class CountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, length = 50)
    private String username;

    @Column(unique = true)
    private String apiKey;

    private BigInteger count;

    public CountEntity() {
    }

    public CountEntity(String username, String apiKey, BigInteger count) {
        this.username = username;
        this.apiKey = apiKey;
        this.count = count;
    }

    public CountEntity(long id, String username, String apiKey, BigInteger count) {
        this.id = id;
        this.username = username;
        this.apiKey = apiKey;
        this.count = count;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public BigInteger getCount() {
        return count;
    }

    public void setCount(BigInteger count) {
        this.count = count;
    }
}
