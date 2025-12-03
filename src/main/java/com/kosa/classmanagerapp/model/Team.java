package com.kosa.classmanagerapp.model;

public class Team extends BaseEntity {

    private String teamName;
    // Project
    private Long projectId;

    public Team() {
    }

    private Team(Builder builder) {
        this.teamName = builder.teamName;
        this.projectId = builder.projectId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getFullName() {
        return "";
    }

    public String getUserName() {

        return "";
    }

    public static class Builder {
        private String teamName;
        private Long projectId;

        public Builder teamName(String teamName) {
            this.teamName = teamName;
            return this;
        }

        public Builder projectId(Long projectId) {
            this.projectId = projectId;
            return this;
        }


        public Team build() {
            return new Team(this);
        }
    }

    // getter / setter
    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }


}