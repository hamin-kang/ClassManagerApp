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

    public User() {}
    // Builder 내부 클래스
    public static class Builder {
        private Long id;
        private String userName;
        private String passwordHash;
        private String fullName;
        private Long teamId;
        private LocalDate birthday;
        private UserAuthorization authorization;
        private LocalDateTime createdAt;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder passwordHash(String passwordHash) {
            this.passwordHash = passwordHash;
            return this;
        }

        public Builder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public Builder teamId(Long teamId) {
            this.teamId = teamId;
            return this;
        }

        public Builder birthday(LocalDate birthday) {
            this.birthday = birthday;
            return this;
        }

        public Builder authorization(UserAuthorization authorization) {
            this.authorization = authorization;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public User build() {
            User user = new User();
            user.id = this.id;
            user.userName = this.userName;
            user.passwordHash = this.passwordHash;
            user.fullName = this.fullName;
            user.teamId = this.teamId;
            user.birthday = this.birthday;
            user.authorization = this.authorization;
            user.createdAt = this.createdAt;
            return user;
        }
    }
    public static Builder builder() {
        return new Builder();
    }
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