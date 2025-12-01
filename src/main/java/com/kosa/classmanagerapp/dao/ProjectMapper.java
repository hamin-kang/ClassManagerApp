package com.kosa.classmanagerapp.dao;

import com.kosa.classmanagerapp.model.Project;

public interface ProjectMapper {
    void save(Project project);
    int count();
}
