package com.kosa.classmanagerapp.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class User {
    // id BIGINT
    private Long id;

    // user_name VARCHAR(50) -> userName
    private String userName;

    // password_hash VARCHAR(255) -> passwordHash
    private String passwordHash;

    // full_name VARCHAR(100) -> fullName
    private String fullName;

    // team_id BIGINT (FK) -> teamId
    private Long teamId;

    // birthday DATE -> birthday
    private LocalDate birthday;

    // authorization ENUM('ADMIN', 'USER') -> authorization
    private UserAuthorization authorization;

    // created_at DATETIME -> createdAt
    private LocalDateTime createdAt;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public UserAuthorization getAuthorization() {
        return authorization;
    }

    public void setAuthorization(UserAuthorization authorization) {
        this.authorization = authorization;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}