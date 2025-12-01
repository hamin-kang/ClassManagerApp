package com.kosa.classmanagerapp.service;

import com.kosa.classmanagerapp.model.assignment.Assignment;
import com.kosa.classmanagerapp.model.assignment.AssignmentType;
import com.kosa.classmanagerapp.global.InitData;

import java.util.ArrayList;
import java.util.List;

public class AssignmentService {

    private final List<Assignment> assignments = new ArrayList<>();

    public AssignmentService() {
        assignments.addAll(InitData.createDummyAssignments());
    }

    public List<Assignment> findAll() {
        return assignments;
    }

    public List<Assignment> findByType(AssignmentType type) {
        return assignments.stream()
                .filter(a -> a.getAssignmentType() == type)
                .toList();
    }

}
