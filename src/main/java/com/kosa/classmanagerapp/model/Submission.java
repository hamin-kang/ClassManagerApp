package com.kosa.classmanagerapp.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import java.time.LocalDate;

public class Submission extends BaseEntity {

    private long assignment_id;
    private long submitter_user_id;
    private String content;
    private LocalDate submitted_at;

    // UI Property용, DB에는 저장 안함
    private final BooleanProperty submitted = new SimpleBooleanProperty(false);

    // --- Builder ---
    public static class Builder {
        private long assignment_id;
        private long submitter_user_id;
        private String content;
        private LocalDate submitted_at;

        public Builder assignmentId(long assignment_id) {
            this.assignment_id = assignment_id;
            return this;
        }

        public Builder submitterUserId(long submitter_user_id) {
            this.submitter_user_id = submitter_user_id;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Builder submittedAt(LocalDate submitted_at) {
            this.submitted_at = submitted_at;
            return this;
        }


        public Submission build() {
            return new Submission(this);
        }
    }

    // --- private constructor ---
    private Submission(Builder builder) {
        this.assignment_id = builder.assignment_id;
        this.submitter_user_id = builder.submitter_user_id;
        this.content = builder.content;
        this.submitted_at = builder.submitted_at;

        // submitted_at 기준으로 Property 초기화
        this.submitted.set(this.submitted_at != null);
    }

    // Getter,Setter
    public LocalDate getSubmittedAt() {
        return submitted_at;
    }

    public long getAssignmentId() {
        return assignment_id;
    }
    public long getSubmitterUserId() {
        return submitter_user_id;
    }
    // BooleanProperty 기반 getter/setter
    public boolean isSubmitted() {
        return submitted.get();
    }

    public void setSubmitted(boolean value) {
        submitted.set(value);

        if (value) {
            this.submitted_at = (this.submitted_at != null) ? this.submitted_at : LocalDate.now();
        } else {
            this.submitted_at = null;
        }
    }

}
