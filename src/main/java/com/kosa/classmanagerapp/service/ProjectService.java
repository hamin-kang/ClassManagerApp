package com.kosa.classmanagerapp.service;

import com.kosa.classmanagerapp.model.Project;
import com.kosa.classmanagerapp.global.initData;

import java.util.ArrayList;
import java.util.List;

public class ProjectService {

    private final List<Project> projects = new ArrayList<>();

    public ProjectService() {
        projects.addAll(initData.createDummyProjects());
    }

    public List<Project> findAll() {
        return projects;
    }
}