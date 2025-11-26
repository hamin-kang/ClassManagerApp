package com.kosa.classmanagerapp.model;

import java.time.LocalDate;

public class Submission extends BaseEntity{
    //Assignment(id)
    private long assignment_id;
    //User(id)
    private long submitter_user_id;
    private String content;
    private LocalDate submitted_ad;
    private boolean is_submitted;
    // --- Builder ---
    public static class Builder {
        private long assignment_id;
        private long submitter_user_id;
        private String content;
        private LocalDate submitted_ad;
        private boolean is_submitted;

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

        public Builder submittedAd(LocalDate submitted_ad) {
            this.submitted_ad = submitted_ad;
            return this;
        }

        public Builder isSubmitted(boolean is_submitted) {
            this.is_submitted = is_submitted;
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
        this.submitted_ad = builder.submitted_ad;
        this.is_submitted = builder.is_submitted;
    }

    public LocalDate getSubmittedAt(){
        return submitted_ad;
    }
    public long getAssignmentId(){
        return assignment_id;
    }
    public void setIsSubmitted(boolean is_submitted){
        this.is_submitted = is_submitted;

    }
    public boolean getIsSubmitted(){
        return is_submitted;
    }
}
