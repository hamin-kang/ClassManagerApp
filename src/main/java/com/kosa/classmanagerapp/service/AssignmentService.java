package com.kosa.classmanagerapp.service;

import com.kosa.classmanagerapp.dao.AssignmentMapper;
import com.kosa.classmanagerapp.model.assignment.Assignment;
import com.kosa.classmanagerapp.model.assignment.AssignmentType;
import com.kosa.classmanagerapp.util.SqlSessionManager;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

public class AssignmentService {

    private final List<Assignment> assignments = new ArrayList<>();

    public AssignmentService() {}

    public List<Assignment> findAll() {
        try (SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession()) {
            AssignmentMapper mapper = session.getMapper(AssignmentMapper.class);
            return mapper.findAll(); // DB에서 가져옴
        }
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

    // 발표 순서 업데이트
    public void updatePresentationOrder(Long assignmentId, String orderString) {
        try (SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true)) {
            AssignmentMapper mapper = session.getMapper(AssignmentMapper.class);
            mapper.updatePresentationOrder(assignmentId, orderString);
        }
    }

}
