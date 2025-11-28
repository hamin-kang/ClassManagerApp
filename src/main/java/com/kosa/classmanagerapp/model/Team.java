package com.kosa.classmanagerapp.model;

public class Team extends BaseEntity {

    private String team_name;
    // Project
    private long project_id;
    // User
    private long leader_id;

    public Team() {
    }

    private Team(Builder builder) {
        this.team_name = builder.team_name;
        this.project_id = builder.project_id;
        this.leader_id = builder.leader_id;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String team_name;
        private long project_id;
        private long leader_id;

        public Builder teamName(String team_name) {
            this.team_name = team_name;
            return this;
        }

        public Builder projectId(long project_id) {
            this.project_id = project_id;
            return this;
        }

        public Builder leaderId(long leader_id) {
            this.leader_id = leader_id;
            return this;
        }

        public Team build() {
            return new Team(this);
        }
    }

    // getter / setter
    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public long getProject_id() {
        return project_id;
    }

    public void setProject_id(long project_id) {
        this.project_id = project_id;
    }

    public long getLeader_id() {
        return leader_id;
    }

    public void setLeader_id(long leader_id) {
        this.leader_id = leader_id;
    }
}