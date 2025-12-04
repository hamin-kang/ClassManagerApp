package com.kosa.classmanagerapp.model;

public class SubmissionContent extends BaseEntity {
    Long submissionId;
    String content;
    public SubmissionContent(){}
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(Long submissionId) {
        this.submissionId = submissionId;
    }

}
