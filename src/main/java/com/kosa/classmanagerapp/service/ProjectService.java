package com.kosa.classmanagerapp.service;

import com.kosa.classmanagerapp.dao.ProjectMapper;
import com.kosa.classmanagerapp.model.Project;
import com.kosa.classmanagerapp.global.initData.InitDataMemory;
import com.kosa.classmanagerapp.util.SqlSessionManager;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

public class ProjectService {
    private final List<Project> projects = new ArrayList<>();
    public ProjectService() {}

    public List<Project> findAll() {
        return projects;
    }

    public void save(Project p) {
        try (SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true)) {
            ProjectMapper mapper = session.getMapper(ProjectMapper.class);
            mapper.save(p);
        }
    }

    public boolean isEmpty() {
        try (SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession()) {
            ProjectMapper mapper = session.getMapper(ProjectMapper.class);
            return mapper.count() == 0;
        }
    }
}