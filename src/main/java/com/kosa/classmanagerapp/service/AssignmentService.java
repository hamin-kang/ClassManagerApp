package com.kosa.classmanagerapp.service;

import com.kosa.classmanagerapp.dao.AssignmentMapper;
import com.kosa.classmanagerapp.dao.TeamMapper;
import com.kosa.classmanagerapp.model.Team;
import com.kosa.classmanagerapp.model.assignment.Assignment;
import com.kosa.classmanagerapp.model.assignment.AssignmentType;
import com.kosa.classmanagerapp.global.initData.InitDataMemory;
import com.kosa.classmanagerapp.util.SqlSessionManager;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

public class AssignmentService {

    private final List<Assignment> assignments = new ArrayList<>();

    public AssignmentService() {}

    public List<Assignment> findAll() {
        return assignments;
    }

    public List<Assignment> findByType(AssignmentType type) {
        return assignments.stream()
                .filter(a -> a.getAssignmentType() == type)
                .toList();
    }
    public void save(Assignment assignment){
        try (SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true)) {
            AssignmentMapper mapper = session.getMapper(AssignmentMapper.class);
            mapper.save(assignment);
        }
    }

}
