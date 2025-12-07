package com.kosa.classmanagerapp.dao;

import com.kosa.classmanagerapp.model.assignment.Assignment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AssignmentMapper {
    void save(Assignment assignment);
    void updatePresentationOrder(@Param("id") Long id, @Param("orderString") String orderString);
    List<Assignment> findAll();

    int insertAssignment(Assignment assignment);
    List<Assignment> findAllAssignments();
    List<Long> findAllByClosedFalse();
}
