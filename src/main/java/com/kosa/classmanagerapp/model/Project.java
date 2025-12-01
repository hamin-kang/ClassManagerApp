package com.kosa.classmanagerapp.model;

public class Project extends BaseEntity {

    private String projectName;

    public Project() {
    }

    private Project(Builder builder) {
        this.projectName = builder.projectName;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String projectName;

        public Builder projectName(String projectName) {
            this.projectName = projectName;
            return this;
        }

        public Project build() {
            return new Project(this);
        }
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}