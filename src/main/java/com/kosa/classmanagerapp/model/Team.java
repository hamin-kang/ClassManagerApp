package com.kosa.classmanagerapp.model;

public class Team extends BaseEntity {

    private String teamdName;
    // Project
    private Long projectId;
    // User
    private Long leaderId;

    public Team() {
    }

    private Team(Builder builder) {
        this.teamdName = builder.teamdName;
        this.projectId = builder.projectId;
        this.leaderId = builder.leaderId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String teamdName;
        private Long projectId;
        private Long leaderId;

        public Builder teamName(String teamdName) {
            this.teamdName = teamdName;
            return this;
        }

        public Builder projectId(Long projectId) {
            this.projectId = projectId;
            return this;
        }

        public Builder leaderId(Long leaderId) {
            this.leaderId = leaderId;
            return this;
        }

        public Team build() {
            return new Team(this);
        }
    }

    // getter / setter
    public String getTeamdName() {
        return teamdName;
    }

    public void setTeamdName(String teamdName) {
        this.teamdName = teamdName;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(Long leaderId) {
        this.leaderId = leaderId;
    }
}