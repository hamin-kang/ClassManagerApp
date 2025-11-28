package com.kosa.classmanagerapp.model.assignment;

import com.kosa.classmanagerapp.model.BaseEntity;

import java.time.LocalDate;

public class Assignment extends BaseEntity {

    private String title;
    private String content;
    // User(id)
    private long creator_id;
    private AssignmentType assignmentType;
    // Team(id)
    private long team_id;
    private boolean is_close;
    private String presentation_order_team_id;
    private LocalDate due_Date;

    // 기본 생성자
    public Assignment() {
    }

    // Builder 전용 private 생성자
    private Assignment(Builder builder) {
        this.title = builder.title;
        this.content = builder.content;
        this.creator_id = builder.creator_id;
        this.assignmentType = builder.assignmentType;
        this.team_id = builder.team_id;
        this.is_close = builder.is_close;
        this.presentation_order_team_id = builder.presentation_order_team_id;
        this.due_Date = builder.due_Date;
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

    public long getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(long creator_id) {
        this.creator_id = creator_id;
    }

    public AssignmentType getAssignmentType() {
        return assignmentType;
    }

    public void setAssignmentType(AssignmentType assignmentType) {
        this.assignmentType = assignmentType;
    }

    public long getTeam_id() {
        return team_id;
    }

    public void setTeam_id(long team_id) {
        this.team_id = team_id;
    }

    public boolean isIs_close() {
        return is_close;
    }

    public void setIs_close(boolean is_close) {
        this.is_close = is_close;
    }

    public String getPresentation_order_team_id() {
        return presentation_order_team_id;
    }

    public void setPresentation_order_team_id(String presentation_order_team_id) {
        this.presentation_order_team_id = presentation_order_team_id;
    }

    public LocalDate getDue_Date() {
        return due_Date;
    }

    public void setDue_Date(LocalDate due_Date) {
        this.due_Date = due_Date;
    }

    // Builder 시작 메서드
    public static Builder builder() {
        return new Builder();
    }

    // Builder 클래스
    public static class Builder {
        private String title;
        private String content;
        private long creator_id;
        private AssignmentType assignmentType;
        private long team_id;
        private boolean is_close;
        private String presentation_order_team_id;
        private LocalDate due_Date;

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Builder creator_id(long creator_id) {
            this.creator_id = creator_id;
            return this;
        }

        public Builder assignmentType(AssignmentType assignmentType) {
            this.assignmentType = assignmentType;
            return this;
        }

        public Builder team_id(long team_id) {
            this.team_id = team_id;
            return this;
        }

        public Builder is_close(boolean is_close) {
            this.is_close = is_close;
            return this;
        }

        public Builder presentation_order_team_id(String presentation_order_team_id) {
            this.presentation_order_team_id = presentation_order_team_id;
            return this;
        }

        public Builder due_Date(LocalDate due_Date) {
            this.due_Date = due_Date;
            return this;
        }

        public Assignment build() {
            return new Assignment(this);
        }
    }
}