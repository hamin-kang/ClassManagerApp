package com.kosa.classmanagerapp.model.assignment;

import com.kosa.classmanagerapp.model.BaseEntity;

import java.time.LocalDate;

public class Assignment extends BaseEntity {

    private String title;
    private String content;
    // User(id)
    private Long creatorId;
    private AssignmentType assignmentType;
    // Team(id)
    private Long teamId;
    private Boolean isClose;
    private String presentationOrderTeamId;
    private LocalDate dueDate;

    // 기본 생성자
    public Assignment() {
    }

    // Builder 전용 private 생성자
    private Assignment(Builder builder) {
        this.title = builder.title;
        this.content = builder.content;
        this.creatorId = builder.creatorId;
        this.assignmentType = builder.assignmentType;
        this.teamId = builder.teamId;
        this.isClose = builder.isClose;
        this.presentationOrderTeamId = builder.presentationOrderTeamId;
        this.dueDate = builder.dueDate;
    }

    // Getter / Setter (필요한 것만 골라서 써도 됨)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public AssignmentType getAssignmentType() {
        return assignmentType;
    }

    public void setAssignmentType(AssignmentType assignmentType) {
        this.assignmentType = assignmentType;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Boolean isIsClose() {
        return isClose;
    }

    public void setIsClose(Boolean isClose) {
        this.isClose = isClose;
    }

    public String getPresentationdOrderTeamId() {
        return presentationOrderTeamId;
    }

    public void setPresentationdOrderTeamId(String presentationOrderTeamId) {
        this.presentationOrderTeamId = presentationOrderTeamId;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    // Builder 시작 메서드
    public static Builder builder() {
        return new Builder();
    }

    // Builder 클래스
    public static class Builder {
        private String title;
        private String content;
        private Long creatorId;
        private AssignmentType assignmentType;
        private Long teamId;
        private Boolean isClose;
        private String presentationOrderTeamId;
        private LocalDate dueDate;

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Builder creatorId(Long creatorId) {
            this.creatorId = creatorId;
            return this;
        }

        public Builder assignmentType(AssignmentType assignmentType) {
            this.assignmentType = assignmentType;
            return this;
        }

        public Builder teamId(Long teamId) {
            this.teamId = teamId;
            return this;
        }

        public Builder isClose(Boolean isClose) {
            this.isClose = isClose;
            return this;
        }

        public Builder presentationOrderTeamId(String presentationOrderTeamId) {
            this.presentationOrderTeamId = presentationOrderTeamId;
            return this;
        }

        public Builder dueDate(LocalDate dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        public Assignment build() {
            return new Assignment(this);
        }
    }
}