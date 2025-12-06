package com.kosa.classmanagerapp.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import java.time.LocalDateTime;

public class Submission extends BaseEntity {

    private Long assignmentId;
    private Long submitterUserId;
    private Long teamId;
    private Boolean isSubmitted;
    private LocalDateTime submittedAt;

    private String fullName;
    private Integer submissionCount;

    // MyBatis가 객체 생성 시 사용하는 기본 생성자
    public Submission() {}

    public static Builder builder() {
        return new Builder();
    }
    // --- Builder ---
    public static class Builder {
        private Long assignmentId;
        private Long submitterUserId;
        private Long teamId;
        private Boolean isSubmitted;
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

        public Builder isSubmitted(Boolean isSubmitted){
            this.isSubmitted = isSubmitted;
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
        this.isSubmitted = builder.isSubmitted;
        this.submittedAt = builder.submittedAt;
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

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }

//    총 과제 수 조회 -admin page
//    public Submission(String fullName, Integer submissionCount) {
//        this.fullName = fullName;
//        this.submissionCount = submissionCount;
//    }

    // Getter / Setter
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getSubmissionCount() {
        return submissionCount;
    }
    public void setSubmissionCount(int submissionCount) {
        this.submissionCount = submissionCount;
    }
}
