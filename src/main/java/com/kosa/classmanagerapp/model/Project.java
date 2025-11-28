package com.kosa.classmanagerapp.model;

public class Project extends BaseEntity {

    private String project_name;

    public Project() {
    }

    private Project(Builder builder) {
        this.project_name = builder.project_name;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String project_name;

        public Builder projectName(String project_name) {
            this.project_name = project_name;
            return this;
        }

        public Project build() {
            return new Project(this);
        }
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }
}