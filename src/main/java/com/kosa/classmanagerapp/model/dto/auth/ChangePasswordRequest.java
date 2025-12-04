package com.kosa.classmanagerapp.model.dto.auth;

public class ChangePasswordRequest {
    private final Long id;
    private final String oldPassword; // 기존 비밀번호
    private final String newPassword; // 새 비밀번호

    public ChangePasswordRequest(Long id, String oldPassword, String newPassword) {
        this.id = id;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public Long getId() {
        return id;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }
}
