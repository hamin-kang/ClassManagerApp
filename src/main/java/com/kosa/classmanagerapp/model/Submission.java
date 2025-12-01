package com.kosa.classmanagerapp.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import java.time.LocalDateTime;

public class Submission extends BaseEntity {

    private Long assignmentId;
    private Long submitterUserId;
    private Long teamId;
    private String content;
    private LocalDateTime submittedAt;

    // UI Property용, DB에는 저장 안함
    private final BooleanProperty submitted = new SimpleBooleanProperty(false);

    // MyBatis가 객체 생성 시 사용하는 기본 생성자
    public Submission() {}

    // --- Builder ---
    public static class Builder {
        private Long assignmentId;
        private Long submitterUserId;
        private Long teamId;
        private String content;
        private LocalDateTime submittedAt;

        public Builder assignmentId(Long assignmentId) {
            this.assignmentId = assignmentId;
            return this;
        }

        public Builder submitterUserId(Long submitterUserId) {
            this.submitterUserId = submitterUserId;
            return this;
        }

        public Builder teamId(Long teamId) {
            this.teamId = teamId;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Builder submittedAt(LocalDateTime submittedAt) {
            this.submittedAt = submittedAt;
            return this;
        }

        public Submission build() {
            return new Submission(this);
        }
    }

    // --- Builder 전용 private 생성자 ---
    private Submission(Builder builder) {
        this.assignmentId = builder.assignmentId;
        this.submitterUserId = builder.submitterUserId;
        this.teamId = builder.teamId;
        this.content = builder.content;
        this.submittedAt = builder.submittedAt;

        this.submitted.set(this.submittedAt != null);
    }

    // ===============================================================
    // Getter + Setter
    // ===============================================================

    public Long getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Long assignmentId) {
        this.assignmentId = assignmentId;
    }

    public Long getSubmitterUserId() {
        return submitterUserId;
    }

    public void setSubmitterUserId(Long submitterUserId) {
        this.submitterUserId = submitterUserId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
        this.submitted.set(submittedAt != null);
    }

    // BooleanProperty 기반 getter/setter
    public boolean isSubmitted() {
        return submitted.get();
    }

    public void setSubmitted(boolean value) {
        submitted.set(value);

        if (value) {
            this.submittedAt = (this.submittedAt != null) ? this.submittedAt : LocalDateTime.now();
        } else {
            this.submittedAt = null;
        }
    }
}
